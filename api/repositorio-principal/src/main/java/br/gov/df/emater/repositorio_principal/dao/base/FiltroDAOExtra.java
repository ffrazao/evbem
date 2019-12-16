package br.gov.df.emater.repositorio_principal.dao.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.df.emater.transporte.FiltroDTO;

@SuppressWarnings("unchecked")
public interface FiltroDAOExtra<F, R> {

	CharSequence LIKE_CHAR = "%";

	default Object colecaoOuUnidade(final Object valor) {
		if ((valor instanceof Collection<?>) && !(((Collection<?>) valor).isEmpty())) {
			return ((Collection<?>) valor).size() > 1 ? valor : ((Collection<?>) valor).iterator().next();
		}
		return valor;
	}

	default Predicate criarPredicado(final CriteriaBuilder cb, final From<?, ?> from, final String campo,
			Object valor) {
		if (valor instanceof Collection<?>) {
			if ((valor == null) || ((Collection<?>) valor).isEmpty()) {
				return null;
			}
			// Tratar COLEÇÃO
			boolean usarLike = false;
			final Iterator<Optional<Object>> itr = ((Collection<Optional<Object>>) valor).iterator();
			while (itr.hasNext()) {
				Object vlr = itr.next();
				if ((vlr != null) && (vlr instanceof Optional)) {
					vlr = ((Optional<?>) vlr).isPresent() ? ((Optional<?>) vlr).get() : null;
				}
				if (vlr != null) {
					if (vlr != null) {
						if (!(vlr instanceof String)) {
							break;
						} else if (((String) vlr).contains(FiltroDAOExtra.LIKE_CHAR)) {
							usarLike = true;
							break;
						}
					}
				}
			}
			if (usarLike) {
				final Predicate[] in = ((Collection<Optional<String>>) valor).stream()
						.filter(p -> p.isPresent() && (p.get().trim().length() > 0)).sorted()
						.map(p -> cb.like(from.get(campo), p.get())).toArray(tamanho -> new Predicate[tamanho]);
				return in.length == 0 ? from.get(campo).isNull() : cb.or(in);
			} else {
				final Object[] in = ((Collection<Optional<?>>) valor).stream().filter(Optional::isPresent)
						.map(Optional::get).sorted().toArray(tamanho -> new Object[tamanho]);
				return in.length == 0 ? from.get(campo).isNull() : from.get(campo).in(in);
			}
		} else {
			// Tratar UNIDADE
			if ((valor != null) && (valor instanceof Optional)) {
				valor = ((Optional<?>) valor).isPresent() ? ((Optional<?>) valor).get() : null;
			}
			return valor == null ? from.get(campo).isNull()
					: ((valor instanceof String) && ((String) valor).contains(FiltroDAOExtra.LIKE_CHAR))
							? cb.like(from.get(campo), (String) valor)
							: cb.equal(from.get(campo), valor);
		}
	}

	default <E extends Enum<E>> String enumSetToString(final Collection<Optional<E>> de) {
		return de.isEmpty() ? ""
				: de.stream().filter(Optional::isPresent).map(Optional::get).map(Enum::name).sorted()
						.collect(Collectors.toList()).toString().replaceAll("\\[", "").replaceAll(" ", "")
						.replaceAll("\\]", "");
	}

	Page<R> findByFiltro(F filtro);

	default <T> Page<T> paginar(final FiltroDTO filtro, final TypedQuery<T> query) {
		final int totalRegistros = query.getResultList().size();

		query.setFirstResult((filtro.getPag() - 1) * filtro.getQtd());
		query.setMaxResults(filtro.getQtd());

		final Page<T> result = new PageImpl<T>(query.getResultList(),
				PageRequest.of(filtro.getPag(), filtro.getQtd(), filtro.getDir(), filtro.getOrdem()), totalRegistros);

		return result;
	}

}

package br.gov.df.emater.repositorio_principal.dao.base;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

@SuppressWarnings("unchecked")
public interface FiltroDAOExtra<F, R> {

	Collection<R> findByFiltro(F filtro);

	default void prepararFiltro(List<Predicate> pl, CriteriaBuilder cb, From<?, ?> from, String campo,
			Object valor) {
		if (valor instanceof Collection<?> && !(((Collection<?>) valor).isEmpty())) {
			Optional<Object> primeiro = ((Collection<Optional<Object>>) valor).iterator().next();
			if (primeiro != null && primeiro.isPresent()) {
				Object primeiroValor = primeiro.get();
				if (primeiroValor instanceof String) {
					pl.add(cb.or(((Collection<Optional<String>>) valor).stream()
							.filter(p -> p.isPresent() && p.get().trim().length() > 0)
							.map(p -> cb.like(from.get(campo), p.get())).toArray(tamanho -> new Predicate[tamanho])));
				} else {
					pl.add(from.get(campo).in(((Collection<Optional<Object>>) valor).stream().filter(Optional::isPresent)
							.map(Optional::get).toArray(tamanho -> new Predicate[tamanho])));
				}
			}
		}
	}

}

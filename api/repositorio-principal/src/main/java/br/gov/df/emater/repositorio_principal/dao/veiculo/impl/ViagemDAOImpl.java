package br.gov.df.emater.repositorio_principal.dao.veiculo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Viagem;
import br.gov.df.emater.transporte.veiculo.ViagemFiltroDTO;

public class ViagemDAOImpl implements FiltroDAOExtra<ViagemFiltroDTO, Viagem> {

	@Autowired
	private EntityManager em;

	@Override
	public Page<Viagem> findByFiltro(final ViagemFiltroDTO filtro) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<Viagem> sql = cb.createQuery(Viagem.class);

		final Root<Viagem> root = sql.from(Viagem.class);

		List<Predicate> pl = new ArrayList<>();

		// campo especial de pesquisa
		if (!CollectionUtils.isEmpty(filtro.getPesq())) {
			final Object pesq = this.colecaoOuUnidade(filtro.getPesq());
			pl.add(cb.or(this.criarPredicado(cb, root, "placa", pesq), this.criarPredicado(cb, root, "renavan", pesq)));
		} else {

		}

		// remover predicados nulos
		pl = pl.stream().filter(p -> p != null).collect(Collectors.toList());
		if (pl.size() == 0) {
			// return new ArrayList<>();
		}

		sql.where(pl.toArray(new Predicate[pl.size()]));

		sql.orderBy(Arrays.asList(cb.asc(root.get("placa"))));

		final TypedQuery<Viagem> query = this.em.createQuery(sql);

		query.setFirstResult((filtro.getPag() - 1) * filtro.getQtd());
		query.setMaxResults(filtro.getQtd());

		return (Page<Viagem>) paginar(filtro, query);

	}

}

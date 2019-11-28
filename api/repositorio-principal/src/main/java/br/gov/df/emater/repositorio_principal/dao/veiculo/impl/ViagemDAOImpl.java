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
import org.springframework.util.CollectionUtils;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Viagem;
import br.gov.df.emater.transporte.veiculo.ViagemFiltroDTO;

public class ViagemDAOImpl implements FiltroDAOExtra<ViagemFiltroDTO, Viagem> {

	@Autowired
	private EntityManager em;

	@Override
	public Viagem[] findByFiltro(ViagemFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Viagem> sql = cb.createQuery(Viagem.class);

		Root<Viagem> root = sql.from(Viagem.class);

		List<Predicate> pl = new ArrayList<>();

		// campo especial de pesquisa
		if (!CollectionUtils.isEmpty(filtro.getPesq())) {
			Object pesq = colecaoOuUnidade(filtro.getPesq());
			pl.add(cb.or(criarPredicado(cb, root, "placa", pesq),
					criarPredicado(cb, root, "renavan", pesq)));
		} else {
			
		}

		// remover predicados nulos
		pl = pl.stream().filter(p -> p != null).collect(Collectors.toList());
		if (pl.size() == 0) {
			// return new ArrayList<>();
		}

		sql.where(pl.toArray(new Predicate[pl.size()]));

		sql.orderBy(Arrays.asList(cb.asc(root.get("placa"))));

		TypedQuery<Viagem> query = em.createQuery(sql);

		query.setFirstResult((filtro.getPagina() - 1) * filtro.getTamanho());
		query.setMaxResults(filtro.getTamanho());

		return query.getResultList().stream().toArray(size -> new Viagem[size]);
	}

}
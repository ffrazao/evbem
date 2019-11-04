package br.gov.df.emater.repositorio_principal.dao.principal.impl;

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
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import br.gov.df.emater.transporte.principal.PessoaFiltroDTO;

public class PessoaDAOImpl implements FiltroDAOExtra<PessoaFiltroDTO, Pessoa> {

	@Autowired
	private EntityManager em;

	@Override
	public Pessoa[] findByFiltro(PessoaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pessoa> sql = cb.createQuery(Pessoa.class);

		Root<Pessoa> root = sql.from(Pessoa.class);

		List<Predicate> pl = new ArrayList<>();

		// campo especial de pesquisa
		if (!CollectionUtils.isEmpty(filtro.getPesq())) {
			Object pesq = colecaoOuUnidade(filtro.getPesq());
			pl.add(cb.or(criarPredicado(cb, root, "nome", pesq)/*, criarPredicado(cb, root, "cpf", pesq)*/));
		} else {
			if (!CollectionUtils.isEmpty(filtro.getNome())) {
				pl.add(criarPredicado(cb, root, "nome", colecaoOuUnidade(filtro.getNome())));
			}
			if (!CollectionUtils.isEmpty(filtro.getCpf())) {
				pl.add(criarPredicado(cb, root, "cpf", colecaoOuUnidade(filtro.getCpf())));
			}
		}

		// remover predicados nulos
		pl = pl.stream().filter(p -> p != null).collect(Collectors.toList());
		if (pl.size() == 0) {
			// return new ArrayList<>();
		}

		sql.where(pl.toArray(new Predicate[pl.size()]));

		sql.orderBy(Arrays.asList(cb.asc(root.get("nome"))));

		TypedQuery<Pessoa> query = em.createQuery(sql);

		query.setFirstResult((filtro.getPagina() - 1) * filtro.getTamanho());
		query.setMaxResults(filtro.getTamanho());

		return query.getResultList().stream().toArray(size -> new Pessoa[size]);
	}

}
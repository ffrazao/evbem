package br.gov.df.emater.repositorio_principal.dao.veiculo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.repositorio_principal.entidade.produto.BemPatrimonial;
import br.gov.df.emater.repositorio_principal.entidade.produto.Marca;
import br.gov.df.emater.repositorio_principal.entidade.produto.Modelo;
import br.gov.df.emater.repositorio_principal.entidade.produto.ProdutoTipo;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

public class VeiculoDAOImpl implements FiltroDAOExtra<VeiculoFiltroDTO, Veiculo> {

	@Autowired
	private EntityManager em;

	@Override
	public Veiculo[] findByFiltro(VeiculoFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> sql = cb.createQuery(Veiculo.class);

		Root<Veiculo> root = sql.from(Veiculo.class);
		Join<Veiculo, Produto> join1 = root.join("produto");
		Join<Produto, Marca> join2 = join1.join("marca");
		Join<Produto, Modelo> join3 = join1.join("modelo");
		Join<Modelo, ProdutoTipo> join4 = join3.join("produtoTipo");
		Join<Produto, BemPatrimonial> join5 = join1.join("bemPatrimonial");

		List<Predicate> pl = new ArrayList<>();

		// campo especial de pesquisa
		if (!CollectionUtils.isEmpty(filtro.getPesq())) {
			Object pesq = colecaoOuUnidade(filtro.getPesq());
			pl.add(cb.or(criarPredicado(cb, root, "placa", pesq),
					criarPredicado(cb, root, "renavan", pesq),
					criarPredicado(cb, join5, "identificacaoPatrimonial", pesq)));
		} else {
			if (!CollectionUtils.isEmpty(filtro.getPlaca())) {
				pl.add(criarPredicado(cb, root, "placa", colecaoOuUnidade(filtro.getPlaca())));
			}
			if (!CollectionUtils.isEmpty(filtro.getRenavan())) {
				pl.add(criarPredicado(cb, root, "renavan", colecaoOuUnidade(filtro.getRenavan())));
			}
			if (!CollectionUtils.isEmpty(filtro.getCor())) {
				pl.add(criarPredicado(cb, root, "cor", colecaoOuUnidade(filtro.getCor())));
			}
			if (!CollectionUtils.isEmpty(filtro.getCombustivel())) {
				pl.add(criarPredicado(cb, root, "combustivel", enumSetToString(filtro.getCombustivel())));
			}
			if (!CollectionUtils.isEmpty(filtro.getAnoFabricacao())) {
				pl.add(criarPredicado(cb, root, "anoFabricacao", colecaoOuUnidade(filtro.getAnoFabricacao())));
			}
			if (!CollectionUtils.isEmpty(filtro.getAnoModelo())) {
				pl.add(criarPredicado(cb, root, "anoModelo", colecaoOuUnidade(filtro.getAnoModelo())));
			}
			if (!CollectionUtils.isEmpty(filtro.getNumeroSerie())) {
				pl.add(criarPredicado(cb, join1, "numeroSerie", colecaoOuUnidade(filtro.getNumeroSerie())));
			}
			if (!CollectionUtils.isEmpty(filtro.getMarca())) {
				pl.add(criarPredicado(cb, join2, "nome", colecaoOuUnidade(filtro.getMarca())));
			}
			if (!CollectionUtils.isEmpty(filtro.getModelo())) {
				pl.add(criarPredicado(cb, join3, "nome", colecaoOuUnidade(filtro.getModelo())));
			}
			if (!CollectionUtils.isEmpty(filtro.getProdutoTipo())) {
				pl.add(criarPredicado(cb, join4, "nome", colecaoOuUnidade(filtro.getProdutoTipo())));
			}
			if (!CollectionUtils.isEmpty(filtro.getIdentificacaoPatrimonial())) {
				pl.add(criarPredicado(cb, join5, "identificacaoPatrimonial", colecaoOuUnidade(filtro.getIdentificacaoPatrimonial())));
			}
		}

		// remover predicados nulos
		pl = pl.stream().filter(p -> p != null).collect(Collectors.toList());
		if (pl.size() == 0) {
			// return new ArrayList<>();
		}

		sql.where(pl.toArray(new Predicate[pl.size()]));

		sql.orderBy(Arrays.asList(cb.asc(join4.get("nome")), cb.asc(join3.get("nome")), cb.asc(join2.get("nome")),
				cb.asc(root.get("placa"))));

		TypedQuery<Veiculo> query = em.createQuery(sql);

		query.setFirstResult((filtro.getPagina() - 1) * filtro.getTamanho());
		query.setMaxResults(filtro.getTamanho());

		return query.getResultList().stream().toArray(size -> new Veiculo[size]);
	}

}

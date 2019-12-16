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
import org.springframework.data.domain.Page;
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
	public Page<Veiculo> findByFiltro(final VeiculoFiltroDTO filtro) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<Veiculo> sql = cb.createQuery(Veiculo.class);

		final Root<Veiculo> root = sql.from(Veiculo.class);
		final Join<Veiculo, Produto> join1 = root.join("produto");
		final Join<Produto, Marca> join2 = join1.join("marca");
		final Join<Produto, Modelo> join3 = join1.join("modelo");
		final Join<Modelo, ProdutoTipo> join4 = join3.join("produtoTipo");
		final Join<Produto, BemPatrimonial> join5 = join1.join("bemPatrimonial");

		List<Predicate> pl = new ArrayList<>();

		// campo especial de pesquisa
		if (!CollectionUtils.isEmpty(filtro.getPesq())) {
			final Object pesq = this.colecaoOuUnidade(filtro.getPesq());
			pl.add(cb.or(this.criarPredicado(cb, root, "placa", pesq), this.criarPredicado(cb, root, "renavan", pesq),
					this.criarPredicado(cb, join5, "identificacaoPatrimonial", pesq)));
		} else {
			if (!CollectionUtils.isEmpty(filtro.getPlaca())) {
				pl.add(this.criarPredicado(cb, root, "placa", this.colecaoOuUnidade(filtro.getPlaca())));
			}
			if (!CollectionUtils.isEmpty(filtro.getRenavan())) {
				pl.add(this.criarPredicado(cb, root, "renavan", this.colecaoOuUnidade(filtro.getRenavan())));
			}
			if (!CollectionUtils.isEmpty(filtro.getCor())) {
				pl.add(this.criarPredicado(cb, root, "cor", this.colecaoOuUnidade(filtro.getCor())));
			}
			if (!CollectionUtils.isEmpty(filtro.getCombustivel())) {
				pl.add(this.criarPredicado(cb, root, "combustivel", this.enumSetToString(filtro.getCombustivel())));
			}
			if (!CollectionUtils.isEmpty(filtro.getAnoFabricacao())) {
				pl.add(this.criarPredicado(cb, root, "anoFabricacao",
						this.colecaoOuUnidade(filtro.getAnoFabricacao())));
			}
			if (!CollectionUtils.isEmpty(filtro.getAnoModelo())) {
				pl.add(this.criarPredicado(cb, root, "anoModelo", this.colecaoOuUnidade(filtro.getAnoModelo())));
			}
			if (!CollectionUtils.isEmpty(filtro.getNumeroSerie())) {
				pl.add(this.criarPredicado(cb, join1, "numeroSerie", this.colecaoOuUnidade(filtro.getNumeroSerie())));
			}
			if (!CollectionUtils.isEmpty(filtro.getMarca())) {
				pl.add(this.criarPredicado(cb, join2, "nome", this.colecaoOuUnidade(filtro.getMarca())));
			}
			if (!CollectionUtils.isEmpty(filtro.getModelo())) {
				pl.add(this.criarPredicado(cb, join3, "nome", this.colecaoOuUnidade(filtro.getModelo())));
			}
			if (!CollectionUtils.isEmpty(filtro.getProdutoTipo())) {
				pl.add(this.criarPredicado(cb, join4, "nome", this.colecaoOuUnidade(filtro.getProdutoTipo())));
			}
			if (!CollectionUtils.isEmpty(filtro.getIdentificacaoPatrimonial())) {
				pl.add(this.criarPredicado(cb, join5, "identificacaoPatrimonial",
						this.colecaoOuUnidade(filtro.getIdentificacaoPatrimonial())));
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

		final TypedQuery<Veiculo> query = this.em.createQuery(sql);

		query.setFirstResult((filtro.getPag() - 1) * filtro.getQtd());
		query.setMaxResults(filtro.getQtd());

		return (Page<Veiculo>) paginar(filtro, query);

	}

}

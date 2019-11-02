package br.gov.df.emater.repositorio_principal.dao.veiculo.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public Collection<Veiculo> findByFiltro(VeiculoFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Veiculo> cq = cb.createQuery(Veiculo.class);

		Root<Veiculo> root = cq.from(Veiculo.class);
		Join<Veiculo, Produto> join1 = root.join("produto");
		Join<Produto, Marca> join2 = join1.join("marca");
		Join<Produto, Modelo> join3 = join1.join("modelo");
		Join<Modelo, ProdutoTipo> join4 = join3.join("produtoTipo");
		Join<Produto, BemPatrimonial> join5 = join1.join("bemPatrimonial");

		List<Predicate> pl = new ArrayList<>();

		// campo de pesquisa especial
		if (!CollectionUtils.isEmpty(filtro.getPesq())) {
			filtro.setPlaca(filtro.getPesq());
			filtro.setIdentificacaoPatrimonial(filtro.getPesq());
			filtro.setRenavan(filtro.getPesq());
		}

		if (!CollectionUtils.isEmpty(filtro.getPlaca())) {
			prepararFiltro(pl, cb, root, "placa", filtro.getPlaca());
		}
		if (!CollectionUtils.isEmpty(filtro.getRenavan())) {
			prepararFiltro(pl, cb, root, "renavan", filtro.getRenavan());
		}
		if (!CollectionUtils.isEmpty(filtro.getCor())) {
//			pl.add(cb.equal(root.get("cor"), filtro.getCor()));
			prepararFiltro(pl, cb, root, "cor", filtro.getCor());
		}

		if (!CollectionUtils.isEmpty(filtro.getNumeroSerie())) {
			prepararFiltro(pl, cb, join1, "numeroSerie", filtro.getNumeroSerie());
		}

		if (!CollectionUtils.isEmpty(filtro.getMarca())) {
			prepararFiltro(pl, cb, join2, "nome", filtro.getMarca());
		}

		if (!CollectionUtils.isEmpty(filtro.getModelo())) {
			prepararFiltro(pl, cb, join3, "nome", filtro.getModelo());
		}

		if (!CollectionUtils.isEmpty(filtro.getProdutoTipo())) {
			prepararFiltro(pl, cb, join4, "nome", filtro.getProdutoTipo());
		}

		if (!CollectionUtils.isEmpty(filtro.getIdentificacaoPatrimonial())) {
			prepararFiltro(pl, cb, join5, "identificacaoPatrimonial", filtro.getIdentificacaoPatrimonial());
		}

		if (pl.size() == 0) {
			return null;
		}

		cq.where(pl.toArray(new Predicate[pl.size()]));
		cq.toString();

		TypedQuery<Veiculo> query = em.createQuery(cq);

		return query.getResultList();
	}

}

package br.gov.df.emater.repositorio_principal.dao.veiculo.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.df.emater.repositorio_principal.dao.veiculo.VeiculoDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

public class VeiculoDAOImpl implements VeiculoDAOExtra {

	@Autowired
	private EntityManager em;

	@Override
	public Collection<Veiculo> findByFiltro(VeiculoFiltroDTO filtro) {
		Collection<Veiculo> result = new ArrayList<>();

		result = em.createQuery("from Veiculo", Veiculo.class).getResultList();

		return result;

	}

}

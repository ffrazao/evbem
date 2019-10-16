package br.gov.df.emater.repositorio_principal.dao.veiculo;

import java.util.Collection;

import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

public interface VeiculoDAOExtra {

	Collection<Veiculo> findByFiltro(VeiculoFiltroDTO filtro);

}

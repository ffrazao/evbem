package br.gov.df.emater.repositorio_principal.dao.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

public interface VeiculoDAO extends JpaRepository<Veiculo, Integer>, FiltroDAOExtra<VeiculoFiltroDTO, Veiculo> {

}
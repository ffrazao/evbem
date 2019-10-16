package br.gov.df.emater.repositorio_principal.dao.veiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

public interface VeiculoDAO extends JpaRepository<Veiculo, Integer> {

	List<Veiculo> findByFiltro(VeiculoFiltroDTO filtro);
	
}
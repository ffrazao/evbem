package br.gov.df.emater.repositorio_principal.dao.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Viagem;
import br.gov.df.emater.transporte.veiculo.ViagemFiltroDTO;

public interface ViagemDAO extends JpaRepository<Viagem, Integer>, FiltroDAOExtra<ViagemFiltroDTO, Viagem> {
}
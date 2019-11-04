package br.gov.df.emater.repositorio_principal.dao.principal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import br.gov.df.emater.transporte.principal.PessoaFiltroDTO;

public interface PessoaDAO extends JpaRepository<Pessoa, Integer>, FiltroDAOExtra<PessoaFiltroDTO, Pessoa> {
}
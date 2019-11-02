package br.gov.df.emater.repositorio_principal.dao.principal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.transporte.principal.ProdutoFiltroDTO;

public interface ProdutoDAO extends JpaRepository<Produto, Integer>, FiltroDAOExtra<ProdutoFiltroDTO, Produto> {

}
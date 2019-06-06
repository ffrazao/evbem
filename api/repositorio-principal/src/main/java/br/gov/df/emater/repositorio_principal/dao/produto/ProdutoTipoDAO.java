package br.gov.df.emater.repositorio_principal.dao.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.entidade.produto.ProdutoTipo;

public interface ProdutoTipoDAO extends JpaRepository<ProdutoTipo, Integer> {
}
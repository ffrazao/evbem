package br.gov.df.emater.repositorio_principal.dao.principal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.entidade.principal.Recurso;

public interface RecursoDAO extends JpaRepository<Recurso, Integer> {
}
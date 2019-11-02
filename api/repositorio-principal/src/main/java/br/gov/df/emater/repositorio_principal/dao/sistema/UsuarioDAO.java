package br.gov.df.emater.repositorio_principal.dao.sistema;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;
import br.gov.df.emater.transporte.sistema.UsuarioFiltroDTO;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>, FiltroDAOExtra<UsuarioFiltroDTO, Usuario> {
	
	Usuario findByLogin(String login);
	
}
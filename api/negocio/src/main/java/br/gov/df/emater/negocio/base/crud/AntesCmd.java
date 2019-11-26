package br.gov.df.emater.negocio.base.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;

@Component
public class AntesCmd extends Comando {

	@Autowired
	private UsuarioDAO dao;

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		System.out.println("Antes");
		log().info("=> total de usuarios [" + dao.count() + "]");
	}

}

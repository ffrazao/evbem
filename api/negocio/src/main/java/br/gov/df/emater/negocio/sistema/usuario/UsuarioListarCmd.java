package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;

@Component
public class UsuarioListarCmd extends Comando {

	@Autowired
	private UsuarioDAO dao;

	@Override
	protected void procedimento(Contexto<?, ?> contexto) throws Exception {
		if (contexto.getRequisicao() != null) {
			contexto.setResposta(dao.findById((Integer) contexto.getRequisicao()).orElseGet(null));
		} else {			
			contexto.setResposta(dao.findAll());
		}
	}

}

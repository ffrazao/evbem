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
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		if (ctx.getRequisicao() != null) {
			ctx.setResposta(dao.findById((Integer) ctx.getRequisicao()).orElseGet(null));
		} else {			
			ctx.setResposta(dao.findAll());
		}
	}

}

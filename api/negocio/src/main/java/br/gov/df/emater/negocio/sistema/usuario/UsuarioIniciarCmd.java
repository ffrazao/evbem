package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;

@Component
public class UsuarioIniciarCmd extends Comando {

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		contexto.setResposta(new Usuario());
	}

}

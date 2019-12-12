package br.gov.df.emater.negocio.principal.produto;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;

@Component
public class ProdutoIniciarCmd extends Comando {

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		contexto.setResposta(new Produto());
	}

}

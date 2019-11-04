package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;

@Component
public class PessoaIniciarCmd extends Comando {

	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {

		Pessoa modelo = (Pessoa) ctx.getRequisicao();
		if (modelo == null) {
			modelo = new Pessoa();
		}
		ctx.setResposta(modelo);
	}

}

package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class PessoaFecharCmd extends Comando {

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		contexto.setResposta("Frz!!!");
		System.out.println("Deu certo 3 !!!!");
	}

}

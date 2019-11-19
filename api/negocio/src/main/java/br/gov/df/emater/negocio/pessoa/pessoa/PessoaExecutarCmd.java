package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class PessoaExecutarCmd extends Comando {

	@Override
	protected <k, v> void procedimento(Contexto<k, v> contexto) throws Exception {
		System.out.println("Deu certo 2 !!!!");
	}

}

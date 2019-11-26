package br.gov.df.emater.negocio.base.crud;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class DepoisCmd extends Comando {

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		System.out.println("Depois");
	}

}

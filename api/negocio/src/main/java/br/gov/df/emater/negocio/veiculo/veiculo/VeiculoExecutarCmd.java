package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class VeiculoExecutarCmd extends Comando {

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		System.out.println("Deu certo 2 !!!!");
	}

}

package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class VeiculoFecharCmd extends Comando {

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		contexto.setResposta("Frz!!!");
		System.out.println("Deu certo 3 !!!!");
	}

}

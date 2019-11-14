package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class ViagemFecharCmd extends Comando {

	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		ctx.setResposta("Frz!!!");
		System.out.println("Deu certo 3 !!!!");
	}

}

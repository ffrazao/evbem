package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class ViagemFecharCmd extends Comando {

	@Override
	protected <k, v> void procedimento(Contexto<k, v> contexto) throws Exception {
		contexto.setResposta("Frz!!!");
		System.out.println("Deu certo 3 !!!!");
	}

}

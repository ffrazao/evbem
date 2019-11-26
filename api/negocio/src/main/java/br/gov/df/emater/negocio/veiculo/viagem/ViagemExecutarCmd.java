package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class ViagemExecutarCmd extends Comando {

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		System.out.println("Deu certo 2 !!!!");
	}

}

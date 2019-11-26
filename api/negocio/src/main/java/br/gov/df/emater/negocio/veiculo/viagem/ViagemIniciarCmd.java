package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Viagem;

@Component
public class ViagemIniciarCmd extends Comando {

	@Override
	protected void procedimento(Contexto contexto) throws Exception {

		Viagem modelo = (Viagem) contexto.getRequisicao();
		if (modelo == null) {
			modelo = new Viagem();
		}

		contexto.setResposta(modelo);
	}

}

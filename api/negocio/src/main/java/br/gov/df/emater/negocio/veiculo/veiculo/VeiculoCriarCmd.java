package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;

@Component
public class VeiculoCriarCmd extends Comando {

	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		ctx.setResposta(new Veiculo());
	}

}

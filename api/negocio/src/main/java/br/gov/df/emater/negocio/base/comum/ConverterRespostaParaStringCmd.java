package br.gov.df.emater.negocio.base.comum;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class ConverterRespostaParaStringCmd extends Comando {

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		Object resposta = contexto.getResposta();
		String result = null;

		if (resposta instanceof Collection) {
			result = resposta == null ? "" : StringUtils.join(((Collection<?>) resposta).iterator(), ",");
		} else {
			result = resposta == null ? "" : resposta.toString();
		}
		contexto.setResposta(result);
	}

}

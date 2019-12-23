package br.gov.df.emater.negocio.base.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.negocio.base.Constantes;

@Component
public class AlterarCmd extends Comando {

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		final Object requisicao = contexto.getRequisicao();
		final Object salvo = contexto.get(Constantes.ID_LIST_RESULT);
		Object instancia = null;
		if (salvo != null) {

			if (salvo instanceof Integer) {
				// recuperar pelo id
			} else {

			}
		}
		instancia = salvo == null ? BeanUtils.instantiateClass((Class<?>) contexto.get("entidade")) : salvo;
		contexto.setResposta(instancia);
	}

}

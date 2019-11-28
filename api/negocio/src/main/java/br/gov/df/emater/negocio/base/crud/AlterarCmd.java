package br.gov.df.emater.negocio.base.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;

@Component
public class AlterarCmd extends Comando {

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		Object modelo = contexto.getRequisicao();
		Object instancia = null;
		if (modelo != null) {

			if (modelo instanceof Integer) {
				// recuperar pelo id
			} else {

			}
		}
		instancia = modelo == null ? BeanUtils.instantiateClass((Class<?>) contexto.get("entidade")) : modelo;
		contexto.setResposta(instancia);
	}

}

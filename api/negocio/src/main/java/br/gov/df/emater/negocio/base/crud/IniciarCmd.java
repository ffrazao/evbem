package br.gov.df.emater.negocio.base.crud;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;

@Component
public class IniciarCmd extends Comando {

	@Override
	@SuppressWarnings("unchecked")
	protected void procedimento(Contexto contexto) throws Exception {
		Dep<?, ?, ?, ?> dep = ((Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA)).get();
		Object modelo = contexto.getRequisicao();
		Object instancia = null;
		if (modelo != null) {

			if (modelo instanceof Integer) {
				// recuperar pelo id
			} else {

			}
		}
		instancia = modelo == null ? dep.getEntidade().newInstance() : modelo;
		contexto.setResposta(instancia);
	}

}

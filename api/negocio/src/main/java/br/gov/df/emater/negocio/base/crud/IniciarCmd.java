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
		Object modelo = contexto.getRequisicao();
		if (modelo != null) {

			if (modelo instanceof Integer) {
				// recuperar pelo id
			} else {

			}
		} else {			
			Optional<Dep<?, ?, ?, ?>> dep = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);
			if (dep.isPresent()) {
				modelo = dep.get().getEntidade().newInstance();
			}
		}
		contexto.setResposta(modelo);
	}

}

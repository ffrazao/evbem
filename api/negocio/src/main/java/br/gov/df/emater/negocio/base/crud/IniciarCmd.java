package br.gov.df.emater.negocio.base.crud;

import static br.gov.df.emater.comum.UtilitarioCollection.deMapStringParaMapObject;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;

@Component
public class IniciarCmd extends Comando {

	@Override
	@SuppressWarnings("unchecked")
	protected void procedimento(final Contexto contexto) throws Exception {
		Object result = null;

		final Optional<Dep<?, ?, ?, ?>> dep = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);

		final Map<String, Object> modelo = (Map<String, Object>) contexto.getRequisicao();
		if ((modelo != null) && !modelo.isEmpty()) {
			if (modelo.get("id") != null) {
				// recuperar pelo id
			} else {
				result = new ObjectMapper().convertValue(deMapStringParaMapObject(modelo), dep.get().getEntidade());
			}
		} else {
			if (dep.isPresent()) {
				result = BeanUtils.instantiateClass(dep.get().getEntidade());
			}
		}

		contexto.setResposta(result);
	}

}

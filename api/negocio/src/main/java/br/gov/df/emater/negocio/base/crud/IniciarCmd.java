package br.gov.df.emater.negocio.base.crud;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;

@Component
public class IniciarCmd extends Comando {

	@Override
	@SuppressWarnings("unchecked")
	protected void procedimento(Contexto contexto) throws Exception {
		Object result = null;
		
		Optional<Dep<?, ?, ?, ?>> dep = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);
		
		Map<String, String> modelo = (Map<String, String>) contexto.getRequisicao();
		if (modelo != null && !modelo.isEmpty()) {
			if (modelo.get("id") != null) {
				// recuperar pelo id
			} else {
				modelo = converteCampoArray(modelo);
				
				ObjectMapper om = new ObjectMapper();
				result = om.convertValue(modelo, dep.get().getEntidade());
			}
		} else {
			if (dep.isPresent()) {
				result = dep.get().getEntidade().newInstance();
			}
		}

		contexto.setResposta(result);
	}

	private Map<String, String> converteCampoArray(Map<String, String> modelo) {
		// FIXME terminar este código
		Map<String, String> result = new LinkedHashMap<String, String>();
		
		Map<String, Map<String, String>> arrays = new LinkedHashMap<String, Map<String, String>>();
		
		// pesquisar itens com o formato de array
		for (Entry<String, String> c: modelo.entrySet()) {
			String item = c.getKey(); 
			if (item.contains("[")) {
				item = item.substring(0, item.indexOf("]"));
				arrays.put(item, c.getValue());
			} else {
				result.put(c.getKey(), c.getValue());
			}
		}
		
		for (Entry<String, String> a: arrays.entrySet()) {
			modelo.put(a.getKey().substring(0, a.getKey().indexOf("[")), a.getValue());
		}
		
		return result;
	}

}

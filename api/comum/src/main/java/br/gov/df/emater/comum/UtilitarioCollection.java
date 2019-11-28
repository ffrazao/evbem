package br.gov.df.emater.comum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class UtilitarioCollection {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> deMapStringParaMapObject(final Map<String, Object> modelo) {
		// FIXME terminar este código
		final Map<String, Object> resultTemp = new LinkedHashMap<>();

		final Map<String, Map<String, Object>> arrays = new LinkedHashMap<>();

		// pesquisar itens com o formato de array
		for (final Entry<String, Object> c : modelo.entrySet()) {
			String item = c.getKey();
			if (item.contains("[") && (item.indexOf("]", item.indexOf("[")) >= 0)) {
				String subCampo = item.substring(item.indexOf("]", item.indexOf("[")) + 1, item.length());
				subCampo = subCampo.startsWith(".") ? subCampo.substring(1) : subCampo;
				item = item.substring(0, item.indexOf("]") + 1);
				if (!arrays.containsKey(item)) {
					arrays.put(item, new LinkedHashMap<>());
				}
				arrays.get(item).put(subCampo, c.getValue());
			} else {
				resultTemp.put(c.getKey(), c.getValue());
			}
		}

		// adicionar os arrays captados
		for (final Entry<String, Map<String, Object>> a : arrays.entrySet()) {
			final String chave = a.getKey().substring(0, a.getKey().indexOf("["));
			if (!resultTemp.containsKey(chave)) {
				resultTemp.put(chave, new ArrayList<>());
			}
			((List<Object>) resultTemp.get(chave)).add(UtilitarioCollection.deMapStringParaMapObject(a.getValue()));
		}

		final Map<String, Object> result = new LinkedHashMap<>();

		final Map<String, Map<String, Object>> objetos = new LinkedHashMap<>();

		// adicionar os objetos identificados
		for (final Entry<String, Object> c : resultTemp.entrySet()) {
			if (c.getKey().contains(".")) {
				final String[] obj = c.getKey().split("\\.");
				if (!objetos.containsKey(obj[0])) {
					objetos.put(obj[0], new LinkedHashMap<String, Object>());
				}
				objetos.get(obj[0]).put(obj[1], c.getValue());
			} else {
				result.put(c.getKey(), c.getValue());
			}
		}

		for (final Entry<String, Map<String, Object>> a : objetos.entrySet()) {
			result.put(a.getKey(), a.getValue());
		}

		return result;
	}

}

package br.gov.df.emater.negocio.base.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.negocio.base.Constantes;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;

@Component
public class RestaurarCmd extends Comando {

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		List<Integer> ids = null;
		
		boolean acessoViaIdList = true;
		ids = (List<Integer>) contexto.get(Constantes.ID_LIST);
		if (ids == null) {
			ids = (List<Integer>) contexto.getRequisicao();
			acessoViaIdList = false;
		}

		if (ids.size() > Constantes.MAX_REGISTROS) {
			throw new IllegalArgumentException("Comando limitado a restaurar " + Constantes.MAX_REGISTROS + " unidades por vez");
		}

		final Dep<?, ?, ?, ?> dep = ((Optional<Dep<?, ?, ?, ?>>) contexto.get(IncluirMapaDependenciaCmd.DEPENDENCIA))
				.get();

		Collection<EntidadeBase> banco = (Collection<EntidadeBase>) dep.getDao().findAllById(ids);
		
		List<EntidadeBase> result = new ArrayList<>();
		for (Integer id: ids) {
			Optional<EntidadeBase> entidade = banco.stream().filter(e -> id.equals(e.getId())).findFirst();
			result.add(entidade.orElse(null));
		}

		if (acessoViaIdList) {
			contexto.put(Constantes.ID_LIST_RESULT, banco);
		} else {
			contexto.setResposta(banco);
		}
	}

}

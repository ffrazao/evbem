package br.gov.df.emater.negocio.base.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;

@Component
public class ExcluirCmd extends Comando {

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {

		final List<Integer> ids = (List<Integer>) contexto.getRequisicao();

		final Dep<?, ?, ?, ?> dep = ((Optional<Dep<?, ?, ?, ?>>) contexto.get(IncluirMapaDependenciaCmd.DEPENDENCIA)).get();

		ids.stream().forEach(id -> dep.getDao().deleteById(id));

	}

}

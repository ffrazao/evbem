package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;

@Component
public class ConverterInfoBasicaCmd extends Comando {

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {

		final Collection<EntidadeBase> entidades = (Collection<EntidadeBase>) contexto.getResposta();

		List<EntidadeBase> result = entidades.stream().map(e -> e.infoBasica()).collect(Collectors.toList());

		contexto.setResposta(result);

	}

}

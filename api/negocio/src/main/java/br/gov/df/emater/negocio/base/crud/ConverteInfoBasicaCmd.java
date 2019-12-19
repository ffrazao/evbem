package br.gov.df.emater.negocio.base.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;

@Component
public class ConverteInfoBasicaCmd extends Comando {

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		final List<EntidadeBase> entidades = (List<EntidadeBase>) contexto.getResposta();
		
		List<EntidadeBase> result = entidades.stream().map(e -> e.infoBasica()).collect(Collectors.toList());

		contexto.setResposta(result);
		
		Integer tamanho = contexto.getRespostaTamanhoHistorico();
		contexto.getResposta(0);
		contexto.getResposta(1);
		contexto.getResposta(tamanho);
		contexto.getResposta(-1);
		contexto.getResposta(100);
		
	}

}

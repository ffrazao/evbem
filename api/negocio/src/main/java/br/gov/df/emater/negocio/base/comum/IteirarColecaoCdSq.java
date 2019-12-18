package br.gov.df.emater.negocio.base.comum;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.negocio.base.Constantes;

@Component
public class IteirarColecaoCdSq extends CadeiaSequencial {

	public static final String ITERADOR = "IteirarColecaoCdSq_iterador";

	@Override
	protected <k, v> boolean antesProcedimento(Contexto contexto) {
		Iterator<?> iterator = (Iterator<?>) contexto.get(ITERADOR);

		if (iterator == null) {
			iterator = ((Collection<?>) contexto.getRequisicao()).iterator();
			contexto.put(ITERADOR, iterator);
		}
		contexto.put(Constantes.ENTIDADE, iterator.next());

		return super.antesProcedimento(contexto);
	}

	@Override
	protected <k, v> boolean vaiRepetir(Contexto contexto) {
		Iterator<?> iterator = (Iterator<?>) contexto.get(ITERADOR);

		return iterator.hasNext();
	}

}

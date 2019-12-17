package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.negocio.base.Constantes;

@Component
public class PrepararCdSq extends CadeiaSequencial {

	public static final String PREPARAR_INTERATOR = "prepararInterator";

	@Override
	protected <k, v> boolean antesProcedimento(Contexto contexto) {
		Iterator<?> iterator = (Iterator<?>) contexto.get(PREPARAR_INTERATOR);

		if (iterator == null) {
			iterator = ((Collection<?>) contexto.getRequisicao()).iterator();
			contexto.put(PREPARAR_INTERATOR, iterator);
		}
		contexto.put(Constantes.ENTIDADE, iterator.next());

		return super.antesProcedimento(contexto);
	}

	@Override
	protected <k, v> boolean vaiRepetir(Contexto contexto) {
		Iterator<?> iterator = (Iterator<?>) contexto.get(PREPARAR_INTERATOR);

		return iterator.hasNext();
	}

}

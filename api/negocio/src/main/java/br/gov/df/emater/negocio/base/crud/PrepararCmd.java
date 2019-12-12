package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeUnicaUtil;

@Component
public class PrepararCmd extends Comando {

	@Autowired
	private EntidadeUnicaUtil entidadeUnica;

	@Override
	protected <k, v> void procedimento(Contexto contexto) throws Exception {
		if (contexto.getRequisicao() instanceof Collection<?>) {
			((Collection<?>) contexto.getRequisicao()).forEach(e -> {
				if (e instanceof EntidadeBase) {			
					entidadeUnica.executar((EntidadeBase) e);
				}
			});
		}
	}

}

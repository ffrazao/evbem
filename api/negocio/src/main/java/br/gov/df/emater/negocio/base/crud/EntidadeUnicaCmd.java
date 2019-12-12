package br.gov.df.emater.negocio.base.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.negocio.base.Constantes;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeUnicaUtil;

@Component
public class EntidadeUnicaCmd extends Comando {

	@Autowired
	private EntidadeUnicaUtil entidadeUnica;

	@Override
	protected <k, v> void procedimento(final Contexto contexto) throws Exception {		
		this.entidadeUnica.executar(contexto.get(Constantes.ENTIDADE));
	}

}

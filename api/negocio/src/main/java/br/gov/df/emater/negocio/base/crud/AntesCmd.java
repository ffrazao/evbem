package br.gov.df.emater.negocio.base.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.MapaDep;

@Component
public class AntesCmd extends Comando {

	public static final String DEPENDENCIA = "dependencia";
	
	@Autowired
	private MapaDep mapa;

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		log().info("Identificação das dependencias");
		contexto.put(DEPENDENCIA, mapa.getDep(contexto.getCatalogo()));
	}

}

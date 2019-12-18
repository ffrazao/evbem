package br.gov.df.emater.negocio.base.crud;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.base.MapaDep;

@Component
public class IncluirMapaDependenciaCmd extends Comando {

	public static final String DEPENDENCIA = "IncluirMapaDependenciaCmd_dependencia";

	@Autowired
	private MapaDep mapa;

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		this.log().info("Identificação das dependencias");
		final Optional<Dep<?, ?, ?, ?>> dep = this.mapa.getDep(contexto.getCatalogo());
		contexto.put(IncluirMapaDependenciaCmd.DEPENDENCIA, dep);
	}

}

package br.gov.df.emater.negocio.base.crud;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.dao.base.FiltroDAOExtra;
import br.gov.df.emater.transporte.FiltroDTO;

@Component
public class ListarCmd extends Comando {

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {

		final Dep<?, ?, ?, ?> dep = ((Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA)).get();

		final FiltroDTO filtro = (FiltroDTO) contexto.getRequisicao();

		final Page<?> result = ((FiltroDAOExtra<FiltroDTO, ?>) dep.getDao()).findByFiltro(filtro);

		contexto.setResposta(result);

	}

}

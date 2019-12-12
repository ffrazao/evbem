package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;

@Component
public class CriarCmd extends Comando {

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void procedimento(Contexto contexto) throws Exception {

		final Optional<Dep<?, ?, ?, ?>> depOpt = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);

		if (depOpt.isPresent()) {
			final Dep<?, ?, ?, ?> dep = depOpt.get();

			Collection<Integer> result = ((Collection<EntidadeBase>) contexto.getRequisicao()).stream()
					.map((reg) -> salvar(dep, reg)).collect(Collectors.toList());

			contexto.setResposta(result);
		}

	}

	private Integer salvar(Dep<?, ?, ?, ?> dep, EntidadeBase entidade) {
		return entidade.getId();
	}

}

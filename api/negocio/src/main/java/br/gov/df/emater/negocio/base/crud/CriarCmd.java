package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;

@Component
public class CriarCmd extends Comando {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void procedimento(Contexto contexto) throws Exception {

		final Optional<Dep<?, ?, ?, ?>> depOpt = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);

		if (depOpt.isPresent()) {
			final Dep<?, ?, ?, ?> dep = depOpt.get();

			contexto.setResposta(((Collection<?>) contexto.getRequisicao()).stream()
					.map((reg) -> ((JpaRepository) dep.getDao()).saveAndFlush(reg)).collect(Collectors.toList()));
		}
	}

}

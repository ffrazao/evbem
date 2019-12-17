package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;

@Component
public class RestaurarCmd extends Comando {

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		final List<Integer> ids = (List<Integer>) contexto.getRequisicao();

		final Dep<?, ?, ?, ?> dep = ((Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA)).get();

		Collection<?> result = dep.getDao().findAllById(ids);

		contexto.setResposta(result);
	}

	// @formatter:off
	/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Integer restaurar(final Dep<?, ?, ?, ?> dep, EntidadeBase entidade, EntidadeBase pai) {
		Set<Dep<?, ?, ?, ?>> subDeps = dep.getDependencias().get();

		// salvar dependencias que a entidade necessita
		final BeanWrapper wrapper = new BeanWrapperImpl(entidade);
		for (Dep<?, ?, ?, ?> depSub : subDeps) {
			Object valor = wrapper.getPropertyValue(depSub.getFuncionalidadeCampo());
			if (!(valor instanceof Collection<?>)) {
				restaurar(depSub, (EntidadeBase) valor);
			}
		}

		if (dep.getCampoPai().isPresent()) {
			wrapper.setPropertyValue(dep.getCampoPai().get(), pai);
		}

		((JpaRepository) dep.getDao()).saveAndFlush(entidade);

		// salvar dependencias necessitam da entidade
		for (Dep<?, ?, ?, ?> depSub : subDeps) {
			Object valor = wrapper.getPropertyValue(depSub.getFuncionalidadeCampo());
			if (valor instanceof Collection<?>) {
				if (!((Collection) valor).isEmpty()) {
					((Collection) valor).forEach(v -> restaurar(depSub, (EntidadeBase) v, entidade));
				}
			}
		}
		return entidade.getId();
	}*/
	// @formatter:on

}

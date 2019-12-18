package br.gov.df.emater.negocio.base.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.negocio.base.Constantes;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;

@Component
public class CriarCmd extends Comando {

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {

		final Dep<?, ?, ?, ?> dep = ((Optional<Dep<?, ?, ?, ?>>) contexto.get(IncluirMapaDependenciaCmd.DEPENDENCIA)).get();

		final EntidadeBase entidade = (EntidadeBase) contexto.get(Constantes.ENTIDADE);

		final Integer id = this.salvar(dep, entidade);

		List<Integer> idList = (List<Integer>) contexto.getResposta();
		if (idList == null) {
			idList = new ArrayList<>();
			contexto.setResposta(idList);
		}
		
		idList.add(id);
	}

	private Integer salvar(final Dep<?, ?, ?, ?> dep, EntidadeBase entidade) {
		return salvar(dep, entidade, null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Integer salvar(final Dep<?, ?, ?, ?> dep, EntidadeBase entidade, EntidadeBase pai) {
		Set<Dep<?, ?, ?, ?>> subDeps = dep.getDependencias().get();

		// salvar dependencias que a entidade necessita
		final BeanWrapper wrapper = new BeanWrapperImpl(entidade);
		for (Dep<?, ?, ?, ?> depSub : subDeps) {
			Object valor = wrapper.getPropertyValue(depSub.getFuncionalidadeCampo());
			if (!(valor instanceof Collection<?>)) {
				salvar(depSub, (EntidadeBase) valor);
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
					((Collection) valor).forEach(v -> salvar(depSub, (EntidadeBase) v, entidade));
				}
			}
		}
		return entidade.getId();
	}

}

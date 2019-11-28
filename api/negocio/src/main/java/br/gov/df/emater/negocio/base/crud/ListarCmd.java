package br.gov.df.emater.negocio.base.crud;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaFisica;

@Component
public class ListarCmd extends Comando {

	@Override
	@SuppressWarnings("unchecked")
	protected void procedimento(Contexto contexto) throws Exception {
		Object modelo = contexto.getRequisicao();
		if (modelo != null) {
			if (modelo instanceof Integer) {
				// recuperar pelo id
			} else {

			}
		} else {
			Optional<Dep<?, ?, ?, ?>> dep = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);
			if (dep.isPresent()) {
				modelo = BeanUtils.instantiateClass(dep.get().getEntidade());
			}
		}
		contexto.setResposta(new PessoaFisica[] { new PessoaFisica() });
	}

}

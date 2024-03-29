package br.gov.df.emater.negocio.veiculo.viagem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.veiculo.ViagemDAO;

@Component
public class ViagemExcluirCmd extends Comando {

	@Autowired
	private ViagemDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		if (contexto.getRequisicao() != null) {
			if (contexto.getRequisicao() instanceof Integer) {
				this.dao.deleteById((Integer) contexto.getRequisicao());
			}
			if (contexto.getRequisicao() instanceof List) {
				((List<Integer>) contexto.getRequisicao()).forEach(id -> this.dao.deleteById(id));
			}
		}
	}

}

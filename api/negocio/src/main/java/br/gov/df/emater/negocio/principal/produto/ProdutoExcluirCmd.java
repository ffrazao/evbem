package br.gov.df.emater.negocio.principal.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.principal.ProdutoDAO;

@Component
public class ProdutoExcluirCmd extends Comando {

	@Autowired
	private ProdutoDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	protected <k, v> void procedimento(Contexto<k, v> contexto) throws Exception {
		if (contexto.getRequisicao() != null) {
			if (contexto.getRequisicao() instanceof Integer) {
				dao.deleteById((Integer) contexto.getRequisicao());
			}
			if (contexto.getRequisicao() instanceof List) {
				((List<Integer>) contexto.getRequisicao()).forEach(id -> dao.deleteById(id));
			}
		}
	}

}

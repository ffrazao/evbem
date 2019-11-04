package br.gov.df.emater.negocio.pessoa.pessoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.principal.PessoaDAO;

@Component
public class PessoaExcluirCmd extends Comando {

	@Autowired
	private PessoaDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		if (ctx.getRequisicao() != null) {
			if (ctx.getRequisicao() instanceof Integer) {
				dao.deleteById((Integer) ctx.getRequisicao());
			}
			if (ctx.getRequisicao() instanceof List) {
				((List<Integer>) ctx.getRequisicao()).forEach(id -> dao.deleteById(id));
			}
		}
	}

}

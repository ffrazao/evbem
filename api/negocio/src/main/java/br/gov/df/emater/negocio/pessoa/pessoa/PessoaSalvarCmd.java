package br.gov.df.emater.negocio.pessoa.pessoa;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.principal.PessoaDAO;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;

@Component
public class PessoaSalvarCmd extends Comando {

	@Autowired
	private PessoaDAO dao;

	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		if (ctx.getRequisicao() instanceof Collection) {
			ctx.setResposta(((Collection<?>) ctx.getRequisicao()).stream()
					.map(reg -> dao.saveAndFlush(prepara((Pessoa) reg))).collect(Collectors.toList()));
		} else {
			ctx.setResposta(dao.saveAndFlush(prepara((Pessoa) ctx.getRequisicao())));
		}
	}

	private Pessoa prepara(Pessoa registro) {
		return registro;
	}

}

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

	private Pessoa prepara(final Pessoa registro) {
		return registro;
	}

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		if (contexto.getRequisicao() instanceof Collection) {
			contexto.setResposta(((Collection<?>) contexto.getRequisicao()).stream()
					.map(reg -> this.dao.saveAndFlush(this.prepara((Pessoa) reg))).collect(Collectors.toList()));
		} else {
			contexto.setResposta(this.dao.saveAndFlush(this.prepara((Pessoa) contexto.getRequisicao())));
		}
	}

}

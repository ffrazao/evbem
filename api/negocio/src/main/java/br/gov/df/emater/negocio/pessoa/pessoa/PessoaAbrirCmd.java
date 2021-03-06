package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.principal.PessoaDAO;
import br.gov.df.emater.repositorio_principal.dao.sistema.TokenDAO;

@Component
public class PessoaAbrirCmd extends Comando {

	@Autowired
	private PessoaDAO dao;

	@Autowired
	private TokenDAO tDao;

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		System.out.printf("Deu certo 1 !!!! total Pessoas [%d] token [%d]", this.dao.count(), this.tDao.count());
	}

}

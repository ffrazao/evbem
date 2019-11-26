package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.sistema.TokenDAO;
import br.gov.df.emater.repositorio_principal.dao.veiculo.ViagemDAO;

@Component
public class ViagemAbrirCmd extends Comando {

	@Autowired
	private ViagemDAO dao;

	@Autowired
	private TokenDAO tDao;

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		System.out.printf("Deu certo 1 !!!! total viagems [%d] token [%d]", dao.count(), tDao.count());
	}

}

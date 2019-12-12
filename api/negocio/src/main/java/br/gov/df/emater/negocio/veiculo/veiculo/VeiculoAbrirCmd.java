package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.sistema.TokenDAO;
import br.gov.df.emater.repositorio_principal.dao.veiculo.VeiculoDAO;

@Component
public class VeiculoAbrirCmd extends Comando {

	@Autowired
	private VeiculoDAO dao;

	@Autowired
	private TokenDAO tDao;

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		System.out.printf("Deu certo 1 !!!! total veiculos [%d] token [%d]", this.dao.count(), this.tDao.count());
	}

}

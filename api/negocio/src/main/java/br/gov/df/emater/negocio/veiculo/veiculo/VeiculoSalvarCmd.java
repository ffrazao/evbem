package br.gov.df.emater.negocio.veiculo.veiculo;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.veiculo.VeiculoDAO;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;

@Component
public class VeiculoSalvarCmd extends Comando {

	@Autowired
	private VeiculoDAO dao;

	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		if (ctx.getRequisicao() instanceof Collection) {
			ctx.setResposta(((Collection<?>) ctx.getRequisicao()).stream()
					.map(reg -> dao.saveAndFlush(prepara((Veiculo) reg))).collect(Collectors.toList()));
		} else {
			ctx.setResposta(dao.saveAndFlush(prepara((Veiculo) ctx.getRequisicao())));
		}
	}

	private Veiculo prepara(Veiculo registro) {
		return registro;
	}

}

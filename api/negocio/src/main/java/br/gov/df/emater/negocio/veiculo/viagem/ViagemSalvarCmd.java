package br.gov.df.emater.negocio.veiculo.viagem;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.veiculo.ViagemDAO;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Viagem;

@Component
public class ViagemSalvarCmd extends Comando {

	@Autowired
	private ViagemDAO dao;

	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		if (ctx.getRequisicao() instanceof Collection) {
			ctx.setResposta(((Collection<?>) ctx.getRequisicao()).stream()
					.map(reg -> dao.saveAndFlush(prepara((Viagem) reg))).collect(Collectors.toList()));
		} else {
			ctx.setResposta(dao.saveAndFlush(prepara((Viagem) ctx.getRequisicao())));
		}
	}

	private Viagem prepara(Viagem registro) {
		return registro;
	}

}

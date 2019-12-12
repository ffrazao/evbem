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

	private Veiculo prepara(final Veiculo registro) {
		return registro;
	}

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		if (contexto.getRequisicao() instanceof Collection) {
			contexto.setResposta(((Collection<?>) contexto.getRequisicao()).stream()
					.map(reg -> this.dao.saveAndFlush(this.prepara((Veiculo) reg))).collect(Collectors.toList()));
		} else {
			contexto.setResposta(this.dao.saveAndFlush(this.prepara((Veiculo) contexto.getRequisicao())));
		}
	}

}

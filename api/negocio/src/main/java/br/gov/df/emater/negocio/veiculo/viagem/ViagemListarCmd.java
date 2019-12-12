package br.gov.df.emater.negocio.veiculo.viagem;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.veiculo.ViagemDAO;
import br.gov.df.emater.transporte.veiculo.ViagemFiltroDTO;

@Component
public class ViagemListarCmd extends Comando {

	@Autowired
	private ViagemDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(final Contexto contexto) throws Exception {

		if (contexto.getRequisicao() != null) {
			if (contexto.getRequisicao() instanceof Integer) {
				contexto.setResposta(this.dao.findById((Integer) contexto.getRequisicao()).orElse(null));
			} else if (contexto.getRequisicao() instanceof List) {
				contexto.setResposta(((List<Integer>) contexto.getRequisicao()).stream()
						.map(id -> this.dao.findById(id).orElse(null)).collect(Collectors.toList()));
			} else {
				final ViagemFiltroDTO filtro = (ViagemFiltroDTO) contexto.getRequisicao();
				contexto.setResposta(this.dao.findByFiltro(filtro));
			}
		} else {
			contexto.setResposta(this.dao.findAll());
		}
	}

}

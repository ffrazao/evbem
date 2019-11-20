package br.gov.df.emater.negocio.veiculo.veiculo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.veiculo.VeiculoDAO;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

@Component
public class VeiculoListarCmd extends Comando {

	@Autowired
	private VeiculoDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	protected <k, v> void procedimento(Contexto<k, v> contexto) throws Exception {

		if (contexto.getRequisicao() != null) {
			if (contexto.getRequisicao() instanceof Integer) {
				contexto.setResposta(dao.findById((Integer) contexto.getRequisicao()).orElse(null));
			} else if (contexto.getRequisicao() instanceof List) {
				contexto.setResposta(((List<Integer>) contexto.getRequisicao()).stream().map(id -> dao.findById(id).orElse(null))
						.collect(Collectors.toList()));
			} else {
				VeiculoFiltroDTO filtro = (VeiculoFiltroDTO) contexto.getRequisicao();
				contexto.setResposta(dao.findByFiltro(filtro));
			}
		} else {
			contexto.setResposta(dao.findAll());
		}
	}

}

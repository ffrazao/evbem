package br.gov.df.emater.negocio.sistema.usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;
import br.gov.df.emater.transporte.sistema.UsuarioFiltroDTO;

@Component
public class UsuarioListarCmd extends Comando {

	@Autowired
	private UsuarioDAO dao;

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
				final UsuarioFiltroDTO filtro = (UsuarioFiltroDTO) contexto.getRequisicao();
				contexto.setResposta(this.dao.findByFiltro(filtro));
			}
		} else {
			contexto.setResposta(this.dao.findAll());
		}
	}

}

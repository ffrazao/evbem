package br.gov.df.emater.negocio.principal.produto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.principal.ProdutoDAO;
import br.gov.df.emater.repositorio_principal.dao.produto.MarcaDAO;
import br.gov.df.emater.repositorio_principal.dao.produto.ModeloDAO;
import br.gov.df.emater.repositorio_principal.dominio.principal.RecursoTipo;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.repositorio_principal.entidade.principal.Recurso;

@Component
public class ProdutoSalvarCmd extends Comando {

	@Autowired
	private ProdutoDAO dao;

	@Autowired
	private MarcaDAO marcaDAO;

	@Autowired
	private ModeloDAO modeloDAO;

	private Produto prepara(final Produto produto) {
		Recurso recurso = produto.getRecurso();
		if (recurso == null) {
			recurso = new Recurso();
		}
		recurso.setRecursoTipo(RecursoTipo.PRODUTO);
		produto.setRecurso(recurso);
		produto.setModelo(this.modeloDAO.getOne(produto.getModelo().getId()));
		produto.setMarca(this.marcaDAO.getOne(produto.getMarca().getId()));
		return produto;
	}

	@Override
	protected void procedimento(final Contexto contexto) throws Exception {
		if (contexto.getRequisicao() instanceof Collection) {
			contexto.setResposta(((Collection<?>) contexto.getRequisicao()).stream()
					.map(reg -> this.dao.saveAndFlush(this.prepara((Produto) reg))).collect(Collectors.toList()));
		} else {
			contexto.setResposta(this.dao.saveAndFlush(this.prepara((Produto) contexto.getRequisicao())));
		}
	}

}

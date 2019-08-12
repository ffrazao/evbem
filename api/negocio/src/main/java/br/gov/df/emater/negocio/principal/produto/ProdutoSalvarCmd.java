package br.gov.df.emater.negocio.principal.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.principal.ProdutoDAO;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;;

@Component
public class ProdutoSalvarCmd extends Comando {

	@Autowired
	private ProdutoDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(Contexto<?, ?> ctx) throws Exception {
		if (ctx.getRequisicao() instanceof Produto) {
			ctx.setResposta(dao.saveAndFlush((Produto) ctx.getRequisicao()));
		} else {
			ctx.setResposta(((List<Produto>) ctx.getRequisicao()).stream().map(reg -> dao.saveAndFlush(reg))
					.collect(Collectors.toList()));
		}
	}

}

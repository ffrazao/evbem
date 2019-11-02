package br.gov.df.emater.rest.controller.produto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.rest.controller.base.BaseCrudCtrl;
import br.gov.df.emater.transporte.principal.ProdutoFiltroDTO;

@RestController()
@RequestMapping("produto")
public class ProdutoCtrl extends BaseCrudCtrl<Produto, ProdutoFiltroDTO, Produto> {
	
	public ProdutoCtrl() {
		super("Produto");
	}
	
}

package br.gov.df.emater.rest.controller.pessoa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import br.gov.df.emater.rest.controller.base.BaseCrudCtrl;
import br.gov.df.emater.transporte.principal.PessoaFiltroDTO;

@RestController()
@RequestMapping("pessoa")
public class PessoaCtrl extends BaseCrudCtrl<Pessoa, PessoaFiltroDTO, Pessoa> {

	public PessoaCtrl() {
		super("Pessoa");
	}

}

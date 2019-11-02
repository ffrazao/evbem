package br.gov.df.emater.rest.controller.sistema;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;
import br.gov.df.emater.rest.controller.base.BaseCrudCtrl;
import br.gov.df.emater.transporte.sistema.UsuarioFiltroDTO;

@RestController()
@RequestMapping("usuario")
public class UsuarioCtrl extends BaseCrudCtrl<Usuario, UsuarioFiltroDTO, Usuario> {

	public UsuarioCtrl() {
		super("Usuario");
	}

}
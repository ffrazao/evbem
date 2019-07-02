package br.gov.df.emater.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.negocio.base.NegocioFacade;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;

@RestController()
@RequestMapping("usuario")
public class UsuarioCtrl {

	@Autowired
	private NegocioFacade negocioFacade;

	@GetMapping
	private List<Usuario> getUsuario() throws Exception {
		return (List<Usuario>) negocioFacade.executarSomenteLeitura("UsuarioListarCdSq");
	}
}

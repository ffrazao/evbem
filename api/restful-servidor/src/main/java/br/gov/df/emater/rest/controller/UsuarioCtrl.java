package br.gov.df.emater.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.negocio.base.NegocioFacade;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;

@SuppressWarnings("unchecked")
@RestController()
@RequestMapping("usuario")
public class UsuarioCtrl {

	@Autowired
	private NegocioFacade negocioFacade;

	@GetMapping
	private List<Usuario> get() throws Exception {
		return (List<Usuario>) negocioFacade.executarSomenteLeitura("UsuarioListarCdSq");
	}

	@GetMapping(value = "/{id}")
	private Usuario get(@PathVariable Integer id) throws Exception {
		return (Usuario) negocioFacade.executarSomenteLeitura("UsuarioListarCdSq", id);
	}

	@PostMapping()
	private Usuario post(@RequestBody Usuario registro) throws Exception {
		return (Usuario) negocioFacade.executarComEscrita("UsuarioSalvarCdSq", registro);
	}
}

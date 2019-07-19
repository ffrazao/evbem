package br.gov.df.emater.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping()
	private Usuario alterar(@RequestBody Usuario registro) throws Exception {
		return (Usuario) negocioFacade.executarComEscrita("UsuarioSalvarCdSq", registro);
	}

	@GetMapping(value = "/criar")
	private Usuario criar() throws Exception {
		return (Usuario) negocioFacade.executarSomenteLeitura("UsuarioCriarCdSq");
	}

	@PutMapping()
	private Usuario incluir(@RequestBody Usuario registro) throws Exception {
		return (Usuario) negocioFacade.executarComEscrita("UsuarioSalvarCdSq", registro);
	}

	@GetMapping
	private List<Usuario> listar() throws Exception {
		return (List<Usuario>) negocioFacade.executarSomenteLeitura("UsuarioListarCdSq");
	}

	@GetMapping(value = "/{id}")
	private Usuario ver(@PathVariable Integer id) throws Exception {
		Usuario result = (Usuario) negocioFacade.executarSomenteLeitura("UsuarioListarCdSq", id);
		System.out.println(result);
		return result;
	}

	@DeleteMapping(value = "/{ids}")
	private void excluir(@PathVariable List<Integer> ids) throws Exception {
		negocioFacade.executarComEscrita("UsuarioExcluirCdSq", ids);
	}
}

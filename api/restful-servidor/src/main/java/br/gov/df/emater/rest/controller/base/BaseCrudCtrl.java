package br.gov.df.emater.rest.controller.base;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unchecked")
public class BaseCrudCtrl<E, F, L> extends BaseCtrl {

	@Setter(value = AccessLevel.NONE)
	private String funcionalidade;

	@PutMapping
	protected List<E> alterar(@RequestBody(required = true) List<E> registros, Principal usuario) throws Exception {
		return (List<E>) negocioFacade.executarComEscrita(String.format("%sSalvarCdSq", funcionalidade), registros,
				usuario);
	}

	@GetMapping(value = "/criar")
	protected E criar(E modelo, Principal usuario) throws Exception {
		return (E) negocioFacade.executarSomenteLeitura(String.format("%sCriarCdSq", funcionalidade), modelo, usuario);
	}

	@DeleteMapping(value = "/{ids}")
	protected void excluir(@PathVariable(name = "ids", required = true) List<Integer> ids, Principal usuario)
			throws Exception {
		negocioFacade.executarComEscrita(String.format("%sExcluirCdSq", funcionalidade), ids, usuario);
	}

	@PostMapping()
	protected List<E> incluir(@RequestBody(required = true) List<E> registros, Principal usuario) throws Exception {
		return (List<E>) negocioFacade.executarComEscrita(String.format("%sSalvarCdSq", funcionalidade), registros,
				usuario);
	}

	// Para passar objetos para metodos get via querystring não é necessário anotar
	// o item com @RequestParam
	// veja: http://dolszewski.com/spring/how-to-bind-requestparam-to-object
	// exemplo de chamada deste método via postman
	// GET http://localhost:8080/veiculo?bemPatrimonial=teste de valores&marca=teste
	// de valores&modelo
	@GetMapping()
	protected List<L> listar(@Valid F filtro, Principal usuario) throws Exception {
		return (List<L>) negocioFacade.executarSomenteLeitura(String.format("%sListarCdSq", funcionalidade), filtro,
				usuario);
	}

	@PostMapping(value = "/salvar")
	protected List<E> salvar(@RequestBody(required = true) List<E> registros, Principal usuario) throws Exception {
		return (List<E>) negocioFacade.executarComEscrita(String.format("%sSalvarCdSq", funcionalidade), registros,
				usuario);
	}

	@GetMapping(value = "/{ids}")
	protected List<E> ver(@PathVariable(name = "ids", required = true) List<Integer> ids, Principal usuario)
			throws Exception {
		return (List<E>) negocioFacade.executarSomenteLeitura(String.format("%sListarCdSq", funcionalidade), ids,
				usuario);
	}

}

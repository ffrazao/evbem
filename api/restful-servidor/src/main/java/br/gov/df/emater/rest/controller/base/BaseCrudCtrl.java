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

import br.gov.df.emater.negocio.base.AlterarEntidade;
import br.gov.df.emater.negocio.base.ExcluirEntidade;
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unchecked")
// E, F, R = Entidade, Filtro, Resultado
public class BaseCrudCtrl<E extends Identificavel, F, R> extends BaseCtrl {

	public static final String SALVAR = "salvar";

	public static final String LISTAR = "listar";

	public static final String EXCLUIR = "excluir";

	public static final String ALTERAR = "alterar";

	public static final String RESTAURAR = "restaurar";

	public static final String CRIAR = "criar";

	public static final String INICIAR = "iniciar";
	
	@Setter(value = AccessLevel.NONE)
	private String funcionalidade;

	@GetMapping(value = "/iniciar")
	protected E iniciar(E modelo, Principal usuario) throws Exception {
		return (E) negocioFacade.executarSomenteLeitura(funcionalidade, BaseCrudCtrl.INICIAR, modelo, usuario);
	}

	@PostMapping()
	protected List<E> criar(@RequestBody(required = true) List<E> entidades, Principal usuario) throws Exception {
		return (List<E>) negocioFacade.executarComEscrita(funcionalidade, BaseCrudCtrl.CRIAR, entidades, usuario);
	}

	@GetMapping(value = "/{ids}")
	protected List<E> restaurar(@PathVariable(name = "ids", required = true) Integer[] ids, Principal usuario)
			throws Exception {
		return (List<E>) negocioFacade.executarSomenteLeitura(funcionalidade, BaseCrudCtrl.RESTAURAR, ids, usuario);
	}

	@PutMapping
	protected AlterarEntidade<E>[] alterar(@RequestBody(required = true) AlterarEntidade<E>[] entidades,
			Principal usuario) throws Exception {
		return (AlterarEntidade<E>[]) negocioFacade.executarComEscrita(funcionalidade, BaseCrudCtrl.ALTERAR, entidades, usuario);
	}

	@DeleteMapping(value = "/{ids}")
	protected ExcluirEntidade[] excluir(@PathVariable(name = "ids", required = true) Integer[] ids, Principal usuario)
			throws Exception {
		return (ExcluirEntidade[]) negocioFacade.executarComEscrita(funcionalidade, BaseCrudCtrl.EXCLUIR, ids, usuario);
	}

	// Para passar objetos para metodos get via querystring não é necessário anotar
	// o item com @RequestParam
	// veja: http://dolszewski.com/spring/how-to-bind-requestparam-to-object
	// exemplo de chamada deste método via postman
	// GET http://localhost:8080/veiculo?bemPatrimonial=teste de valores&marca=teste
	// de valores&modelo
	@GetMapping()
	protected R[] listar(@Valid F filtro, Principal usuario) throws Exception {
		return (R[]) negocioFacade.executarSomenteLeitura(funcionalidade, BaseCrudCtrl.LISTAR, filtro, usuario);
	}

	@PostMapping(value = "/salvar")
	protected List<E> salvar(@RequestBody(required = true) List<E> entidades, Principal usuario) throws Exception {
		return (List<E>) negocioFacade.executarComEscrita(funcionalidade, BaseCrudCtrl.SALVAR, entidades, usuario);
	}

}

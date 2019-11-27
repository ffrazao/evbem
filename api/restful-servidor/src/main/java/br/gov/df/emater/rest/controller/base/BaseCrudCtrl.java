package br.gov.df.emater.rest.controller.base;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

	public static final String ALTERAR = "alterar";

	public static final String CRIAR = "criar";

	public static final String EXCLUIR = "excluir";

	public static final String INICIAR = "iniciar";

	public static final String LISTAR = "listar";

	public static final String RESTAURAR = "restaurar";

	public static final String SALVAR = "salvar";

	@Setter(value = AccessLevel.NONE)
	private final String funcionalidade;

	@PutMapping
	protected AlterarEntidade<E>[] alterar(@RequestBody(required = true) final AlterarEntidade<E>[] entidades,
			final Principal usuario) throws Exception {
		return (AlterarEntidade<E>[]) this.negocioFacade.executarComEscrita(this.funcionalidade, BaseCrudCtrl.ALTERAR,
				entidades, usuario);
	}

	@PostMapping()
	protected List<E> criar(@RequestBody(required = true) final List<E> entidades, final Principal usuario)
			throws Exception {
		return (List<E>) this.negocioFacade.executarComEscrita(this.funcionalidade, BaseCrudCtrl.CRIAR, entidades,
				usuario);
	}

	@DeleteMapping(value = "/{ids}")
	protected ExcluirEntidade[] excluir(@PathVariable(name = "ids", required = true) final Integer[] ids,
			final Principal usuario) throws Exception {
		return (ExcluirEntidade[]) this.negocioFacade.executarComEscrita(this.funcionalidade, BaseCrudCtrl.EXCLUIR, ids,
				usuario);
	}

	@GetMapping(value = "/" + BaseCrudCtrl.INICIAR)
	protected E iniciar(@RequestParam final Map<String, String> modelo, final Principal usuario) throws Exception {
		return (E) this.negocioFacade.executarSomenteLeitura(this.funcionalidade, BaseCrudCtrl.INICIAR, modelo,
				usuario);
	}

	// Para passar objetos para metodos get via querystring não é necessário anotar
	// o item com @RequestParam
	// veja: http://dolszewski.com/spring/how-to-bind-requestparam-to-object
	// exemplo de chamada deste método via postman
	// GET http://localhost:8080/veiculo?bemPatrimonial=teste de valores&marca=teste
	// de valores&modelo
	@GetMapping()
	protected R[] listar(@Valid final F filtro, final Principal usuario) throws Exception {
		return (R[]) this.negocioFacade.executarSomenteLeitura(this.funcionalidade, BaseCrudCtrl.LISTAR, filtro,
				usuario);
	}

	@GetMapping(value = "/{ids}")
	protected List<E> restaurar(@PathVariable(name = "ids", required = true) final Integer[] ids,
			final Principal usuario) throws Exception {
		return (List<E>) this.negocioFacade.executarSomenteLeitura(this.funcionalidade, BaseCrudCtrl.RESTAURAR, ids,
				usuario);
	}

	@PostMapping(value = "/" + BaseCrudCtrl.SALVAR)
	protected List<E> salvar(@RequestBody(required = true) final List<E> entidades, final Principal usuario)
			throws Exception {
		return (List<E>) this.negocioFacade.executarComEscrita(this.funcionalidade, BaseCrudCtrl.SALVAR, entidades,
				usuario);
	}

}

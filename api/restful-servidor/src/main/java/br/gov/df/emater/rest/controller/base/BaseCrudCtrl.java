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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unchecked")
// E, F, R = Entidade, Filtro, Resultado
public class BaseCrudCtrl<E extends Identificavel, F, R> extends BaseCtrl implements Crud<E, F, R> {

	public BaseCrudCtrl(final String funcionalidade) {
		super(funcionalidade);
	}

	@Override
	@PutMapping
	public List<AlterarEntidade<E>> alterar(@RequestBody(required = true) final List<AlterarEntidade<E>> entidades,
			final Principal usuario) throws Exception {
		return (List<AlterarEntidade<E>>) this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(),
				Crud.ALTERAR, entidades, usuario);
	}

	@Override
	@PostMapping()
	public List<E> criar(@RequestBody(required = true) final List<E> entidades, final Principal usuario)
			throws Exception {
		return (List<E>) this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(), Crud.CRIAR, entidades,
				usuario);
	}

	@Override
	@DeleteMapping(value = "/{ids}")
	public List<ExcluirEntidade> excluir(@PathVariable(name = "ids", required = true) final List<Integer> ids,
			final Principal usuario) throws Exception {
		return (List<ExcluirEntidade>) this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(),
				Crud.EXCLUIR, ids, usuario);
	}

	@Override
	@GetMapping(value = "/" + Crud.INICIAR)
	public E iniciar(@RequestParam final Map<String, Object> modelo, final Principal usuario) throws Exception {
		return (E) this.getNegocioFacade().executarSomenteLeitura(this.getFuncionalidade(), Crud.INICIAR, modelo,
				usuario);
	}

	// Para passar objetos para metodos get via querystring não é necessário anotar
	// o item com @RequestParam
	// veja: http://dolszewski.com/spring/how-to-bind-requestparam-to-object
	// exemplo de chamada deste método via postman
	// GET http://localhost:8080/veiculo?bemPatrimonial=teste de valores&marca=teste
	// de valores&modelo
	@Override
	@GetMapping()
	public List<R> listar(@Valid final F filtro, final Principal usuario) throws Exception {
		return (List<R>) this.getNegocioFacade().executarSomenteLeitura(this.getFuncionalidade(), Crud.LISTAR, filtro,
				usuario);
	}

	@Override
	@GetMapping(value = "/{ids}")
	public List<E> restaurar(@PathVariable(name = "ids", required = true) final List<Integer> ids,
			final Principal usuario) throws Exception {
		return (List<E>) this.getNegocioFacade().executarSomenteLeitura(this.getFuncionalidade(), Crud.RESTAURAR, ids,
				usuario);
	}

	@Override
	@PostMapping(value = "/" + Crud.SALVAR)
	public List<E> salvar(@RequestBody(required = true) final List<E> entidades, final Principal usuario)
			throws Exception {
		return (List<E>) this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(), Crud.SALVAR, entidades,
				usuario);
	}

}

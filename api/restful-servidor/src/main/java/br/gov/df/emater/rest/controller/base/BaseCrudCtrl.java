package br.gov.df.emater.rest.controller.base;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.df.emater.negocio.base.NegocioException;
import br.gov.df.emater.negocio.base.NegocioIntegridadeDadosException;
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
import br.gov.df.emater.transporte.FiltroDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unchecked")
// E, F, R = Entidade, Filtro, Resultado
public class BaseCrudCtrl<E extends Identificavel, F extends FiltroDTO, R> extends BaseCtrl implements Crud<E, F, R> {

	public BaseCrudCtrl(final String funcionalidade) {
		super(funcionalidade);
	}

	@Override
	@PutMapping(value = "/{ids}")
	public ResponseEntity<Void> alterar(@Valid @PathVariable(name = "ids", required = true) final List<Integer> ids,
			@Valid @RequestBody(required = true) final List<E> entidades, final Principal usuario) throws Exception {
		// garantir que os itens modificados serão os informados
		if (ids.size() != entidades.size()) {
			throw new NegocioException(
					"O quantitativo de chaves não corresponde ao quantitativo de entidades enviadas");
		}
		if (ids.size() == 0) {
			throw new NegocioException("Dados não enviados!");
		}
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i) == null || entidades.get(i) == null) {
				throw new NullPointerException("Id ou Entidade nula!");
			}
			entidades.get(i).setId(ids.get(i));
		}
		this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(), Crud.ALTERAR, entidades, usuario);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PostMapping()
	public ResponseEntity<Void> criar(@Valid @RequestBody(required = true) final List<E> entidades, final Principal usuario)
			throws Exception {
		// eliminar o valor dos Id's (PK's) para garantir criação de novos registros
		entidades.stream().forEach(e -> e.setId(null));
		List<E> result = (List<E>) this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(), Crud.CRIAR,
				entidades, usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ids}").buildAndExpand(result).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<Void> excluir(@Valid @PathVariable(name = "ids", required = true) final List<Integer> ids, final Principal usuario) throws Exception {
		try {
			this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(), Crud.EXCLUIR, ids, usuario);			
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException e) {
			throw new NegocioIntegridadeDadosException("Não é possível excluir o(s) registro(s), há dados vinculados.", e);
		}
	}

	@Override
	@GetMapping(value = "/" + Crud.INICIAR)
	public ResponseEntity<E> iniciar(@Valid @RequestParam final Map<String, Object> modelo, final Principal usuario)
			throws Exception {
		E result = (E) this.getNegocioFacade().executarSomenteLeitura(this.getFuncionalidade(), Crud.INICIAR, modelo,
				usuario);
		return ResponseEntity.ok(result);
	}

	// Para passar objetos para metodos get via querystring não é necessário anotar
	// o item com @RequestParam
	// veja: http://dolszewski.com/spring/how-to-bind-requestparam-to-object
	// exemplo de chamada deste método via postman
	// GET http://localhost:8080/veiculo?bemPatrimonial=teste de valores&marca=teste
	// de valores&modelo
	@Override
	@GetMapping()
	public ResponseEntity<Page<R>> listar(@Valid final F filtro, final Principal usuario) throws Exception {
		Page<R> result = (Page<R>) this.getNegocioFacade().executarSomenteLeitura(this.getFuncionalidade(), Crud.LISTAR, filtro, usuario);
		return ResponseEntity.ok(result);
	}

	@Override
	@GetMapping(value = "/{ids}")
	public ResponseEntity<List<E>> restaurar(@Valid @PathVariable(name = "ids", required = true) final List<Integer> ids,
			final Principal usuario) throws Exception {
		List<E> result = (List<E>) this.getNegocioFacade().executarSomenteLeitura(this.getFuncionalidade(),
				Crud.RESTAURAR, ids, usuario);
		return ResponseEntity.ok(result);
	}

	@Override
	@PostMapping(value = "/" + Crud.SALVAR)
	public ResponseEntity<List<E>> salvar(@Valid @RequestBody(required = true) final List<E> entidades,
			final Principal usuario) throws Exception {
		List<E> result = (List<E>) this.getNegocioFacade().executarComEscrita(this.getFuncionalidade(), Crud.SALVAR,
				entidades, usuario);
		return ResponseEntity.ok(result);
	}

}

package br.gov.df.emater.rest.controller.base;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
import br.gov.df.emater.transporte.FiltroDTO;

public interface Crud<E extends Identificavel, F extends FiltroDTO, R> {

	String ALTERAR = "alterar";
	String CRIAR = "criar";
	String EXCLUIR = "excluir";
	String INICIAR = "iniciar";
	String LISTAR = "listar";
	String RESTAURAR = "restaurar";
	String SALVAR = "salvar";

	ResponseEntity<Void> alterar(final List<Integer> ids, List<E> entidades, final Principal usuario) throws Exception;

	ResponseEntity<Void> criar(final List<E> entidades, final Principal usuario) throws Exception;

	ResponseEntity<Void> excluir(final List<Integer> ids, final Principal usuario) throws Exception;

	ResponseEntity<E> iniciar(final Map<String, Object> modelo, final Principal usuario) throws Exception;

	ResponseEntity<Page<R>> listar(final F filtro, final Principal usuario) throws Exception;

	ResponseEntity<List<E>> restaurar(final List<Integer> ids, final Principal usuario) throws Exception;

	ResponseEntity<List<E>> salvar(final List<E> entidades, final Principal usuario) throws Exception;

}
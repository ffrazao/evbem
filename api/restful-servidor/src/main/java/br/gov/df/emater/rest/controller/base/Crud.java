package br.gov.df.emater.rest.controller.base;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import br.gov.df.emater.negocio.base.AlterarEntidade;
import br.gov.df.emater.negocio.base.ExcluirEntidade;
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;

public interface Crud<E extends Identificavel, F, R> {

	String ALTERAR = "alterar";
	String CRIAR = "criar";
	String EXCLUIR = "excluir";
	String INICIAR = "iniciar";
	String LISTAR = "listar";
	String RESTAURAR = "restaurar";
	String SALVAR = "salvar";

	List<AlterarEntidade<E>> alterar(List<AlterarEntidade<E>> entidades, Principal usuario) throws Exception;

	List<E> criar(List<E> entidades, Principal usuario) throws Exception;

	List<ExcluirEntidade> excluir(List<Integer> ids, Principal usuario) throws Exception;

	E iniciar(Map<String, Object> modelo, Principal usuario) throws Exception;

	List<R> listar(F filtro, Principal usuario) throws Exception;

	List<E> restaurar(List<Integer> ids, Principal usuario) throws Exception;

	List<E> salvar(List<E> entidades, Principal usuario) throws Exception;

}
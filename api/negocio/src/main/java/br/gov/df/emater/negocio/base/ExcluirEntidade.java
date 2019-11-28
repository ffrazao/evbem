package br.gov.df.emater.negocio.base;

import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;

public class ExcluirEntidade {

	public Integer id;

	public boolean resultado = false;

	public ExcluirEntidade(Identificavel entidade) {
		if (entidade == null || entidade.getId() == null) {
			throw new NullPointerException("Entidade nula ou não identificavel");
		}
		this.id = ((Identificavel) entidade).getId();
	}

	public ExcluirEntidade(Integer id) {
		this.id = id;
	}

}
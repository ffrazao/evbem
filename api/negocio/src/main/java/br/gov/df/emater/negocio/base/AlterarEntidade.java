package br.gov.df.emater.negocio.base;

import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;

public class AlterarEntidade<E extends Identificavel> {

	public E entidade;

	public Integer id;

	public AlterarEntidade(final E entidade) {
		if ((entidade == null) || (entidade.getId() == null)) {
			throw new NullPointerException("Entidade nula ou não identificavel");
		}
		this.entidade = entidade;
		this.id = ((Identificavel) entidade).getId();
	}

}

package br.gov.df.emater.repositorio_principal.entidade.base;

import javax.persistence.Transient;

public interface Nomeavel {

	@Transient
	default Nomeavel copy() {
		Nomeavel result;
		try {
			result = this.getClass().newInstance();
			result.setNome(this.getNome());
			if (this instanceof EntidadeBase) {
				((EntidadeBase) result).setId(((EntidadeBase) this).getId());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public String getNome();

	public void setNome(String nome);

}

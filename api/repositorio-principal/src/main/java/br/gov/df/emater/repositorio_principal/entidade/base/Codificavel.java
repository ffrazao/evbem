package br.gov.df.emater.repositorio_principal.entidade.base;

import javax.persistence.Transient;

public interface Codificavel {

	@Transient
	default Codificavel copy() {
		Codificavel result;
		try {
			result = this.getClass().newInstance();
			result.setCodigo(this.getCodigo());
			if (this instanceof EntidadeBase) {
				((EntidadeBase) result).setId(((EntidadeBase) this).getId());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public String getCodigo();

	public void setCodigo(String codigo);

}

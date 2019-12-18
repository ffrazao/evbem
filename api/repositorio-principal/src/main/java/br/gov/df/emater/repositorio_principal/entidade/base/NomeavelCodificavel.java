package br.gov.df.emater.repositorio_principal.entidade.base;

import javax.persistence.Transient;

public interface NomeavelCodificavel extends Nomeavel, Codificavel {

	@Override
	@Transient
	default NomeavelCodificavel copy() {
		NomeavelCodificavel result;
		try {
			result = this.getClass().newInstance();
			result.setCodigo(this.getCodigo());
			result.setNome(this.getNome());
			if (this instanceof EntidadeBase) {
				((EntidadeBase) result).setId(((EntidadeBase) this).getId());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}

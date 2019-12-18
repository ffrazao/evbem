package br.gov.df.emater.repositorio_principal.entidade.base;

import javax.persistence.Transient;

import br.gov.df.emater.transporte.InfoBasica;

public interface PaiNomeavelCodificavel<T> extends Pai<T>, NomeavelCodificavel {

	@SuppressWarnings("unchecked")
	@Transient
	default PaiNomeavelCodificavel<T> copy() {
		PaiNomeavelCodificavel<T> result;
		try {
			result = (PaiNomeavelCodificavel<T>) this.getClass().newInstance();
			if (this.getPai() != null) {
				if (this.getPai() instanceof InfoBasica) {
					result.setPai((T) ((InfoBasica<?>) this.getPai()).infoBasica());
				} else {
					result.setPai(this.getPai());
				}
			}
			result.setNome(this.getNome());
			result.setCodigo(this.getCodigo());
			if (this instanceof EntidadeBase) {
				((EntidadeBase) result).setId(((EntidadeBase) this).getId());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}

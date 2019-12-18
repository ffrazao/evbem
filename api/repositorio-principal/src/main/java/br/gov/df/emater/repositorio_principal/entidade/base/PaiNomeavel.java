package br.gov.df.emater.repositorio_principal.entidade.base;

import java.util.Optional;

import javax.persistence.Transient;

import br.gov.df.emater.transporte.InfoBasica;

public interface PaiNomeavel<T> extends Pai<T>, Nomeavel {

	@SuppressWarnings("unchecked")
	@Transient
	default PaiNomeavel<T> copy() {
		PaiNomeavel<T> result;
		try {
			result = (PaiNomeavel<T>) this.getClass().newInstance();
			if (this.getPai() != null) {
				if (this.getPai() instanceof InfoBasica) {
					result.setPai((T) ((InfoBasica<?>) this.getPai()).infoBasica());
				} else {
					result.setPai(this.getPai());
				}
			}
			result.setNome(this.getNome());
			if (this instanceof EntidadeBase) {
				((EntidadeBase) result).setId(((EntidadeBase) this).getId());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Transient
	default String getNomeAscendente() {
		final StringBuilder result = new StringBuilder();
		result.append(this.getNome());
		Optional.ofNullable(this.getPai())
				.ifPresent((v) -> result.append("/ ").append(((PaiNomeavel<T>) v).getNomeAscendente()));
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Transient
	default String getNomeDescendente() {
		final StringBuilder result = new StringBuilder();
		Optional.ofNullable(this.getPai())
				.ifPresent((v) -> result.append(((PaiNomeavel<T>) v).getNomeAscendente()).append("/ "));
		result.append(this.getNome());
		return result.toString();
	}

}

package br.gov.df.emater.repositorio_principal.entidade.base;

import java.util.Optional;

import javax.persistence.Transient;

public interface PaiNomeavel<T> extends Pai<T>, Nomeavel {

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

package br.gov.df.emater.repositorio_principal.entidade.base;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Transient;

import br.gov.df.emater.transporte.InfoBasica;

public interface Pai<T> {
	
	@SuppressWarnings("unchecked")
	@Transient
	default Pai<T> copy() {
		Pai<T> result;
		try {
			result = (Pai<T>) this.getClass().newInstance();
			if (this.getPai() != null) {
				if (this.getPai() instanceof InfoBasica) {
					result.setPai((T) ((InfoBasica<?>) this.getPai()).infoBasica());
				} else {					
					result.setPai(this.getPai());
				}
			}
			if (this instanceof EntidadeBase) {
				((EntidadeBase) result).setId(((EntidadeBase) this).getId());
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public default void adicionarFilho(final T t) {
		((Pai<T>) t).setPai((T) this);
		this.getFilhos().add(t);
	}

	@SuppressWarnings("unchecked")
	public default void adicionarFilho(final T... ts) {
		Arrays.stream(ts).forEach(f -> this.adicionarFilho(f));
	}

	public Collection<T> getFilhos();

	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	public T getPai();

	public default void removerFilho() {
		this.getFilhos().forEach(f -> this.removerFilho(f));
	}

	@SuppressWarnings("unchecked")
	public default void removerFilho(final T t) {
		if (this.getFilhos().remove(t)) {
			((Pai<T>) t).setPai((T) null);
		}
	}

	public void setPai(T pai);

}

package br.gov.df.emater.repositorio_principal.entidade.base;

import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public interface Pai<T> {

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

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
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

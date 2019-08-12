package br.gov.df.emater.repositorio_principal.entidade;

import java.util.Collection;

public interface Pai<T> {

	public Collection<T> getFilhos();
		
	public T getPai();
	
	public void setPai(T pai);
			
}

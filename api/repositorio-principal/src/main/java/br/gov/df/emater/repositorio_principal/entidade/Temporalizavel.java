package br.gov.df.emater.repositorio_principal.entidade;

import java.sql.Timestamp;

public interface Temporalizavel {
	
	public Timestamp getInicio();
	
	public Timestamp getTermino();
	
	public void setInicio(Timestamp inicio);
	
	public void setTermino(Timestamp termino);

}

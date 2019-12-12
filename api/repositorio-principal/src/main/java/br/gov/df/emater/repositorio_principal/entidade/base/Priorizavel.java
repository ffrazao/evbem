package br.gov.df.emater.repositorio_principal.entidade.base;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;

public interface Priorizavel extends Ordenavel {

	public Confirmacao getPrincipal();

	public void setPrincipal(Confirmacao nome);

}

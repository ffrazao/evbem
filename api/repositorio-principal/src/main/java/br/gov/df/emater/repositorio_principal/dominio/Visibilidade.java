package br.gov.df.emater.repositorio_principal.dominio;

public enum Visibilidade {
	
	PUBLICO("Público"), PARTICULAR("Particular");

	private String descricao;

	private Visibilidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}

}

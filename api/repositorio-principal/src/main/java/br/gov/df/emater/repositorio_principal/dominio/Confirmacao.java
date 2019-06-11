package br.gov.df.emater.repositorio_principal.dominio;

public enum Confirmacao {
	S("Sim"), N("Não");

	private String descricao;

	private Confirmacao(String descricao) {
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

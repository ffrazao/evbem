package br.gov.df.emater.repositorio_principal.dominio;

public enum FormaAutenticacaoTipo {

	BD("BD"), EXTERNO("Externo"), LDAP("LDAP"), MEMORIA("Memória");

	private String descricao;

	private FormaAutenticacaoTipo(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public String toString() {
		return this.getDescricao();
	}

}

package br.gov.df.emater.repositorio_principal.dominio;

public enum UnidadeOrganizacionalHierarquiaTipo {

	ASSESSORAMENTO("Assessoramento"), GESTAO("Gestão");

	private String descricao;

	private UnidadeOrganizacionalHierarquiaTipo(final String descricao) {
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

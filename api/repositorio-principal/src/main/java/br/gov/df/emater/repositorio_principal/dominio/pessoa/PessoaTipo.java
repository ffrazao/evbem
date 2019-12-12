package br.gov.df.emater.repositorio_principal.dominio.pessoa;

public enum PessoaTipo {

	GS("Grupo Social"), PF("Pessoa Física"), PJ("Pessoa Jurídica");

	private String descricao;

	private PessoaTipo(final String descricao) {
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

package br.gov.df.emater.repositorio_principal.dominio;

public enum UsuarioTipo {

	ADMIN("Administrador do Sistema"), COMUM("Usuário Comum"), SISTEMA("Sistema Externo");

	private String descricao;

	private UsuarioTipo(final String descricao) {
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

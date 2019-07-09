package br.gov.df.emater.repositorio_principal.dominio;

public enum UsuarioTipo {
	
	COMUM("Usuário Comum"),
	SISTEMA("Sistema Externo"),
	ADMIN("Administrador do Sistema");

	private String descricao;

	private UsuarioTipo(String descricao) {
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

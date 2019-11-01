package br.gov.df.emater.repositorio_principal.dominio.principal;

public enum RecursoTipo {
	
	PESSOA("Pessoa"), PRODUTO("Produto"), SERVICO("Serviço");

	private String descricao;

	private RecursoTipo(String descricao) {
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

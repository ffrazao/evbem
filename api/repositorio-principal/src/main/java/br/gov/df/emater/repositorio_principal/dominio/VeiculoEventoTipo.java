package br.gov.df.emater.repositorio_principal.dominio;

public enum VeiculoEventoTipo {
	
	PESSOA("Pessoa"), PRODUTO("Produto"), SERVICO("Serviço");

	private String descricao;

	private VeiculoEventoTipo(String descricao) {
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

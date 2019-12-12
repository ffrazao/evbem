package br.gov.df.emater.repositorio_principal.dominio.veiculo;

public enum Combustivel {

	DIESEL("Diesel"), ETANOL("Etanol"), GASOLINA("Gasolina");

	private String descricao;

	private Combustivel(final String descricao) {
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

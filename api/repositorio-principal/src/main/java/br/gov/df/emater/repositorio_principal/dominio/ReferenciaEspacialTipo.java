package br.gov.df.emater.repositorio_principal.dominio;

public enum ReferenciaEspacialTipo {

	ENDERECO("Endereço"), LOCALIZACAO("Localização");

	private String descricao;

	private ReferenciaEspacialTipo(final String descricao) {
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

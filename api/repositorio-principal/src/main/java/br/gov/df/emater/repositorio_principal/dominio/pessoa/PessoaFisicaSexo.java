package br.gov.df.emater.repositorio_principal.dominio.pessoa;

public enum PessoaFisicaSexo {

	F("Feminino"), M("Masculino");

	private String descricao;

	private PessoaFisicaSexo(final String descricao) {
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

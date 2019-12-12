package br.gov.df.emater.repositorio_principal.dominio;

public enum AcaoTipo {

	A("Acessar"), C("Criar"), D("Deletar"), E("Executar"), R("Restaurar"), U("Atualizar");

	private String descricao;

	private AcaoTipo(final String descricao) {
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

package br.gov.df.emater.repositorio_principal.dominio;

public enum FuncionalidadeAcaoConcedeAcessoA {

	ANONIMO("Anônimo"), PERFIL("Perfil"), PERFIL_FUNCIONAL("Perfil Funcional"), PERFIL_PESSOAL("Perfil Pessoal"),
	SEM_PERFIL("Sem Perfil");

	private String descricao;

	private FuncionalidadeAcaoConcedeAcessoA(final String descricao) {
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

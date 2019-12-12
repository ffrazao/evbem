package br.gov.df.emater.repositorio_principal.dominio.evento;

public enum EvidenciaTipo {

	AUDIO("Áudio"), DOCUMENTO("Documento"), IMAGEM("Imagem"), VIDEO("Vídeo");

	private String descricao;

	private EvidenciaTipo(final String descricao) {
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

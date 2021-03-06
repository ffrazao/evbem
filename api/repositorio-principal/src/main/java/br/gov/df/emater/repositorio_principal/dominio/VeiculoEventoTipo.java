package br.gov.df.emater.repositorio_principal.dominio;

public enum VeiculoEventoTipo {

	ABASTECIMENTO("Abastecimento"), DENUNCIA("Denúncia"), DESPESA("Despesa"), INFRACAO("Infração"),
	LEMBRETE("Lembrete"), MANUTENCAO("Manutenção"), OBSERVACAO("Observação"), SINISTRO("Sinistro"),
	TAXAS_IMPOSTOS("Taxas/impostos"), VIAGEM("Viagem");

	private String descricao;

	private VeiculoEventoTipo(final String descricao) {
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

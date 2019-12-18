package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import static br.gov.df.emater.comum.Constantes.JUNCAO_CAMPO_SET;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import br.gov.df.emater.repositorio_principal.dominio.veiculo.Combustivel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Veiculo database table.
 * 
 */
@Entity
@Table(catalog = "veiculo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Veiculo extends EntidadeBase {

	@Column(name = "ano_fabricacao")
	private Integer anoFabricacao;

	@Column(name = "ano_modelo")
	private Integer anoModelo;

	private String combustivel;

	@Column(name = "cor")
	private String cor;

	@Column(name = "cor_rgb")
	private String corRgb;

	@Id
	private Integer id;

	@Column(name = "placa")
	private String placa;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	private Produto produto;

	@Column(name = "renavan")
	private String renavan;

	public Veiculo(Integer valor) {
		super(valor);
	}

	public Set<Combustivel> getCombustivel() {
		return (StringUtils.isBlank(this.combustivel)) ? Collections.emptySet()
				: Collections.unmodifiableSet(Arrays.stream(this.combustivel.split(JUNCAO_CAMPO_SET))
						.map(c -> Combustivel.valueOf(c)).sorted().collect(Collectors.toSet()));
	}

	@Override
	public Veiculo infoBasica() {
		Veiculo result = (Veiculo) super.infoBasica();
		if (result.getProduto() != null) {
			result.setProduto(result.getProduto().infoBasica());
		}
		return result;
	}

	public void setCombustivel(Set<Combustivel> combustivel) {
		this.combustivel = (combustivel == null) ? null
				: combustivel.stream().map(c -> c.toString()).sorted().collect(Collectors.joining(JUNCAO_CAMPO_SET));
	}

}

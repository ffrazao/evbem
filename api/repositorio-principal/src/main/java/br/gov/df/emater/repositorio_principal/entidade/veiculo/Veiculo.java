package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
public class Veiculo extends EntidadeBase implements Serializable {

	private static final String JUNCAO = ",";

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	private Produto produto;

	@Column(name = "ano_fabricacao")
	private Integer anoFabricacao;

	@Column(name = "ano_modelo")
	private Integer anoModelo;

	private String combustivel;

	@Column(name = "cor")
	private String cor;

	@Column(name = "cor_rgb")
	private String corRgb;

	@Column(name = "placa")
	private String placa;

	@Column(name = "renavan")
	private String renavan;

	public Set<Combustivel> getCombustivel() {
		return (StringUtils.isBlank(this.combustivel)) ? Collections.emptySet()
				: Collections.unmodifiableSet(Arrays.stream(this.combustivel.split(JUNCAO))
						.map(c -> Combustivel.valueOf(c)).sorted().collect(Collectors.toSet()));
	}

	public void setCombustivel(Set<Combustivel> combustivel) {
		this.combustivel = (combustivel == null) ? null
				: combustivel.stream().map(c -> c.toString()).sorted().collect(Collectors.joining(JUNCAO));
	}

}

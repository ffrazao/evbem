package br.gov.df.emater.repositorio_principal.entidade.produto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the bem_patrimonial database table.
 * 
 */
@Entity
@Table(catalog = "produto", name = "bem_patrimonial")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class BemPatrimonial extends EntidadeBase {

	@Id
	private Integer id;

	@Column(name = "identificacao_patrimonial")
	private String identificacaoPatrimonial;

	@Lob
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa; // responsavel pelo bem

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	@JsonIgnore
	private Produto produto;

	@Column(name = "sigla_proprietario")
	private String siglaProprietario;

	public BemPatrimonial(Integer valor) {
		super(valor);
	}

	@Override
	public BemPatrimonial infoBasica() {
		BemPatrimonial result = (BemPatrimonial) super.infoBasica();
		if (result.getPessoa() != null) {
			result.setPessoa((Pessoa) result.getPessoa().copy());
		}
		if (result.getProduto() != null) {
			result.setProduto(result.getProduto().infoBasica());
		}
		return result;
	}

}
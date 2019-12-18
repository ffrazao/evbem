package br.gov.df.emater.repositorio_principal.entidade.produto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(catalog = "produto", name = "produto_pessoa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class ProdutoPessoa extends EntidadeBase {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa; // pode utilizar o bem

	@ManyToOne
	@JoinColumn(name = "produto_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Produto produto;

	public ProdutoPessoa(Integer valor) {
		super(valor);
	}

	@Override
	public ProdutoPessoa infoBasica() {
		ProdutoPessoa result = (ProdutoPessoa) super.infoBasica();
		if (result.getPessoa() != null) {
			result.setPessoa((Pessoa) result.getPessoa().copy());
		}
		if (result.getProduto() != null) {
			result.setProduto(result.getProduto().infoBasica());
		}
		return result;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.produto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the produto_tipo database table.
 * 
 */
@Entity
@Table(catalog = "produto", name = "produto_tipo_marca")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class ProdutoTipoMarca extends EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Marca marca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private ProdutoTipo produtoTipo;

	public ProdutoTipoMarca(Integer valor) {
		super(valor);
	}

	@Override
	public ProdutoTipoMarca infoBasica() {
		ProdutoTipoMarca result = (ProdutoTipoMarca) super.infoBasica();
		if (result.getMarca() != null) {
			result.setMarca(result.getMarca().infoBasica());
		}
		if (result.getProdutoTipo() != null) {
			result.setProdutoTipo(result.getProdutoTipo().infoBasica());
		}
		return result;
	}

}
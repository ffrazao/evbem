package br.gov.df.emater.repositorio_principal.entidade.comum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.produto.ProdutoTipo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the modelo database table.
 * 
 */
@Entity
@Table(catalog = "comum", name = "tipo_unidade_medida")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class ProdutoTipoUnidadeMedida extends EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private ProdutoTipo produtoTipo;

	@ManyToOne
	@JoinColumn(name = "unidade_medida_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeMedida unidadeMedida;

	public ProdutoTipoUnidadeMedida(Integer valor) {
		super(valor);
	}

	@Override
	public ProdutoTipoUnidadeMedida infoBasica() {
		ProdutoTipoUnidadeMedida result = (ProdutoTipoUnidadeMedida) super.infoBasica();
		result.setProdutoTipo(result.produtoTipo.infoBasica());
		result.setUnidadeMedida(result.unidadeMedida.infoBasica());
		return result;
	}

}
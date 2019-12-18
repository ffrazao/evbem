package br.gov.df.emater.repositorio_principal.entidade.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.NomeavelCodificavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the modelo database table.
 * 
 */
@Entity
@Table(catalog = "produto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Modelo extends EntidadeBase implements NomeavelCodificavel {

	private String codigo;

	@Lob
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "produto_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private ProdutoTipo produtoTipo;

	public Modelo(Integer valor) {
		super(valor);
	}

	@Override
	public Modelo infoBasica() {
		Modelo result = (Modelo) copy();
		if (result.getProdutoTipo() != null) {
			result.setProdutoTipo(result.getProdutoTipo().infoBasica());
		}
		result.setDescricao(this.getDescricao());
		return result;
	}

}
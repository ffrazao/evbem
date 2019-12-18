package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.PaiNomeavelCodificavel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the produto_tipo database table.
 * 
 */
@Entity
@Table(catalog = "produto", name = "tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class ProdutoTipo extends EntidadeBase implements PaiNomeavelCodificavel<ProdutoTipo> {

	private String codigo;

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<ProdutoTipo> filhos = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private ProdutoTipo pai;

	public ProdutoTipo(Integer id) {
		setId(id);
	}

	@Override
	public ProdutoTipo infoBasica() {
		ProdutoTipo result = (ProdutoTipo) ((PaiNomeavelCodificavel<ProdutoTipo>) this).copy();
		return result;
	}

}
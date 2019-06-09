package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the produto_tipo database table.
 * 
 */
@Entity
@Table(catalog = "produto", name = "produto_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoTipo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to Modelo
	@OneToMany(mappedBy = "produtoTipo")
	private List<Modelo> modelos;

	private String nome;

	// bi-directional many-to-one association to ProdutoTipo
	@ManyToOne
	@JoinColumn(name = "pai_id")
	private ProdutoTipo produtoTipo;

	// bi-directional many-to-one association to ProdutoTipo
	@OneToMany(mappedBy = "produtoTipo")
	private List<ProdutoTipo> produtoTipos;

}
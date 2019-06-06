package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the produto_tipo database table.
 * 
 */
@Entity
@Table(catalog = "produto", name="produto_tipo")
@NamedQuery(name="ProdutoTipo.findAll", query="SELECT p FROM ProdutoTipo p")
public class ProdutoTipo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nome;

	//bi-directional many-to-one association to Modelo
	@OneToMany(mappedBy="produtoTipo")
	private List<Modelo> modelos;

	//bi-directional many-to-one association to ProdutoTipo
	@ManyToOne
	@JoinColumn(name="pai_id")
	private ProdutoTipo produtoTipo;

	//bi-directional many-to-one association to ProdutoTipo
	@OneToMany(mappedBy="produtoTipo")
	private List<ProdutoTipo> produtoTipos;

	public ProdutoTipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Modelo> getModelos() {
		return this.modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo addModelo(Modelo modelo) {
		getModelos().add(modelo);
		modelo.setProdutoTipo(this);

		return modelo;
	}

	public Modelo removeModelo(Modelo modelo) {
		getModelos().remove(modelo);
		modelo.setProdutoTipo(null);

		return modelo;
	}

	public ProdutoTipo getProdutoTipo() {
		return this.produtoTipo;
	}

	public void setProdutoTipo(ProdutoTipo produtoTipo) {
		this.produtoTipo = produtoTipo;
	}

	public List<ProdutoTipo> getProdutoTipos() {
		return this.produtoTipos;
	}

	public void setProdutoTipos(List<ProdutoTipo> produtoTipos) {
		this.produtoTipos = produtoTipos;
	}

	public ProdutoTipo addProdutoTipo(ProdutoTipo produtoTipo) {
		getProdutoTipos().add(produtoTipo);
		produtoTipo.setProdutoTipo(this);

		return produtoTipo;
	}

	public ProdutoTipo removeProdutoTipo(ProdutoTipo produtoTipo) {
		getProdutoTipos().remove(produtoTipo);
		produtoTipo.setProdutoTipo(null);

		return produtoTipo;
	}

}
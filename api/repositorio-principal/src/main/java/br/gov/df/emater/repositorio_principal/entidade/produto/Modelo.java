package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the modelo database table.
 * 
 */
@Entity
@Table(catalog = "produto")
@NamedQuery(name="Modelo.findAll", query="SELECT m FROM Modelo m")
public class Modelo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	private Marca marca;

	//bi-directional many-to-one association to ProdutoTipo
	@ManyToOne
	@JoinColumn(name="produto_tipo_id")
	private ProdutoTipo produtoTipo;

	public Modelo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public ProdutoTipo getProdutoTipo() {
		return this.produtoTipo;
	}

	public void setProdutoTipo(ProdutoTipo produtoTipo) {
		this.produtoTipo = produtoTipo;
	}

}
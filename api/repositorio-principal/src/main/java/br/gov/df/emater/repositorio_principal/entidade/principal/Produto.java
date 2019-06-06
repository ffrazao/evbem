package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(catalog = "principal")
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="modelo_id")
	private int modeloId;

	@Column(name="numero_serie")
	private String numeroSerie;

	//bi-directional one-to-one association to Item
	@OneToOne
	@JoinColumn(name="id")
	private Item item;

	public Produto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getModeloId() {
		return this.modeloId;
	}

	public void setModeloId(int modeloId) {
		this.modeloId = modeloId;
	}

	public String getNumeroSerie() {
		return this.numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
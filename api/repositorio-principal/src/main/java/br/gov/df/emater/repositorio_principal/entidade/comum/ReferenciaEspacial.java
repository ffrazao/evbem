package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the referencia_espacial database table.
 * 
 */
@Entity
@Table(catalog = "comum", name="referencia_espacial")
@NamedQuery(name="ReferenciaEspacial.findAll", query="SELECT r FROM ReferenciaEspacial r")
public class ReferenciaEspacial extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String area;

	private String tipo;

	//bi-directional one-to-one association to Endereco
	@OneToOne(mappedBy="referenciaEspacial")
	private Endereco endereco;

	//bi-directional one-to-one association to Localizacao
	@OneToOne(mappedBy="referenciaEspacial")
	private Localizacao localizacao;

	public ReferenciaEspacial() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

}
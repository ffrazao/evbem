package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String cep;

	private String complemento;

	private String logradouro;

	private String numero;

	@Lob
	@Column(name="ponto_referencia")
	private String pontoReferencia;

	//bi-directional many-to-one association to Localizacao
	@ManyToOne
	private Localizacao localizacao;

	//bi-directional one-to-one association to ReferenciaEspacial
	@OneToOne
	@JoinColumn(name="id")
	private ReferenciaEspacial referenciaEspacial;

	public Endereco() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPontoReferencia() {
		return this.pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public ReferenciaEspacial getReferenciaEspacial() {
		return this.referenciaEspacial;
	}

	public void setReferenciaEspacial(ReferenciaEspacial referenciaEspacial) {
		this.referenciaEspacial = referenciaEspacial;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the pessoa_juridica database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="pessoa_juridica")
@NamedQuery(name="PessoaJuridica.findAll", query="SELECT p FROM PessoaJuridica p")
public class PessoaJuridica extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String cnpj;

	@Temporal(TemporalType.DATE)
	private Date encerramento;

	@Temporal(TemporalType.DATE)
	private Date fundacao;

	public PessoaJuridica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getEncerramento() {
		return this.encerramento;
	}

	public void setEncerramento(Date encerramento) {
		this.encerramento = encerramento;
	}

	public Date getFundacao() {
		return this.fundacao;
	}

	public void setFundacao(Date fundacao) {
		this.fundacao = fundacao;
	}

}
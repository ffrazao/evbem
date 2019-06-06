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
 * The persistent class for the pessoa_fisica database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="pessoa_fisica")
@NamedQuery(name="PessoaFisica.findAll", query="SELECT p FROM PessoaFisica p")
public class PessoaFisica extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date falecimento;

	@Temporal(TemporalType.DATE)
	private Date nascimento;

	private String sexo;

	public PessoaFisica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getFalecimento() {
		return this.falecimento;
	}

	public void setFalecimento(Date falecimento) {
		this.falecimento = falecimento;
	}

	public Date getNascimento() {
		return this.nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the bem_patrimonial database table.
 * 
 */
@Entity
@Table(catalog = "produto", name="bem_patrimonial")
@NamedQuery(name="BemPatrimonial.findAll", query="SELECT b FROM BemPatrimonial b")
public class BemPatrimonial extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String descricao;

	@Column(name="identificacao_patrimonial")
	private String identificacaoPatrimonial;

	public BemPatrimonial() {
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

	public String getIdentificacaoPatrimonial() {
		return this.identificacaoPatrimonial;
	}

	public void setIdentificacaoPatrimonial(String identificacaoPatrimonial) {
		this.identificacaoPatrimonial = identificacaoPatrimonial;
	}

}
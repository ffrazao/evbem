package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the privilegio database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Privilegio.findAll", query="SELECT p FROM Privilegio p")
public class Privilegio extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@ManyToOne
	@JoinColumn(name="funcionalidade_acao_id")
	private FuncionalidadeAcao funcionalidadeAcao;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	private Perfil perfil;

	public Privilegio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAtivo() {
		return this.ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public FuncionalidadeAcao getFuncionalidadeAcao() {
		return this.funcionalidadeAcao;
	}

	public void setFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		this.funcionalidadeAcao = funcionalidadeAcao;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
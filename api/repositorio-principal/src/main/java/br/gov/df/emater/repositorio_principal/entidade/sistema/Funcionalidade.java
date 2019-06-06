package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the funcionalidade database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Funcionalidade.findAll", query="SELECT f FROM Funcionalidade f")
public class Funcionalidade extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	private String codigo;

	@Lob
	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Funcionalidade
	@ManyToOne
	@JoinColumn(name="pai_id")
	private Funcionalidade funcionalidade;

	//bi-directional many-to-one association to Funcionalidade
	@OneToMany(mappedBy="funcionalidade")
	private List<Funcionalidade> funcionalidades;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@OneToMany(mappedBy="funcionalidade")
	private List<FuncionalidadeAcao> funcionalidadeAcaos;

	public Funcionalidade() {
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

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Funcionalidade getFuncionalidade() {
		return this.funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public List<Funcionalidade> getFuncionalidades() {
		return this.funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public Funcionalidade addFuncionalidade(Funcionalidade funcionalidade) {
		getFuncionalidades().add(funcionalidade);
		funcionalidade.setFuncionalidade(this);

		return funcionalidade;
	}

	public Funcionalidade removeFuncionalidade(Funcionalidade funcionalidade) {
		getFuncionalidades().remove(funcionalidade);
		funcionalidade.setFuncionalidade(null);

		return funcionalidade;
	}

	public List<FuncionalidadeAcao> getFuncionalidadeAcaos() {
		return this.funcionalidadeAcaos;
	}

	public void setFuncionalidadeAcaos(List<FuncionalidadeAcao> funcionalidadeAcaos) {
		this.funcionalidadeAcaos = funcionalidadeAcaos;
	}

	public FuncionalidadeAcao addFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		getFuncionalidadeAcaos().add(funcionalidadeAcao);
		funcionalidadeAcao.setFuncionalidade(this);

		return funcionalidadeAcao;
	}

	public FuncionalidadeAcao removeFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		getFuncionalidadeAcaos().remove(funcionalidadeAcao);
		funcionalidadeAcao.setFuncionalidade(null);

		return funcionalidadeAcao;
	}

}
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
 * The persistent class for the acao database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Acao.findAll", query="SELECT a FROM Acao a")
public class Acao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	private String codigo;

	@Lob
	private String descricao;

	private String nome;

	private int ordem;

	private String tipo;

	//bi-directional many-to-one association to Acao
	@ManyToOne
	@JoinColumn(name="pai_id")
	private Acao acao;

	//bi-directional many-to-one association to Acao
	@OneToMany(mappedBy="acao")
	private List<Acao> acaos;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@OneToMany(mappedBy="acao")
	private List<FuncionalidadeAcao> funcionalidadeAcaos;

	public Acao() {
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

	public int getOrdem() {
		return this.ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public List<Acao> getAcaos() {
		return this.acaos;
	}

	public void setAcaos(List<Acao> acaos) {
		this.acaos = acaos;
	}

	public Acao addAcao(Acao acao) {
		getAcaos().add(acao);
		acao.setAcao(this);

		return acao;
	}

	public Acao removeAcao(Acao acao) {
		getAcaos().remove(acao);
		acao.setAcao(null);

		return acao;
	}

	public List<FuncionalidadeAcao> getFuncionalidadeAcaos() {
		return this.funcionalidadeAcaos;
	}

	public void setFuncionalidadeAcaos(List<FuncionalidadeAcao> funcionalidadeAcaos) {
		this.funcionalidadeAcaos = funcionalidadeAcaos;
	}

	public FuncionalidadeAcao addFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		getFuncionalidadeAcaos().add(funcionalidadeAcao);
		funcionalidadeAcao.setAcao(this);

		return funcionalidadeAcao;
	}

	public FuncionalidadeAcao removeFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		getFuncionalidadeAcaos().remove(funcionalidadeAcao);
		funcionalidadeAcao.setAcao(null);

		return funcionalidadeAcao;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
 * The persistent class for the funcionalidade_acao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="funcionalidade_acao")
@NamedQuery(name="FuncionalidadeAcao.findAll", query="SELECT f FROM FuncionalidadeAcao f")
public class FuncionalidadeAcao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	@Column(name="concede_acesso_a")
	private String concedeAcessoA;

	@Lob
	private String descricao;

	//bi-directional many-to-one association to Acao
	@ManyToOne
	private Acao acao;

	//bi-directional many-to-one association to Funcionalidade
	@ManyToOne
	private Funcionalidade funcionalidade;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@ManyToOne
	@JoinColumn(name="pai_id")
	private FuncionalidadeAcao funcionalidadeAcao;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@OneToMany(mappedBy="funcionalidadeAcao")
	private List<FuncionalidadeAcao> funcionalidadeAcaos;

	//bi-directional many-to-one association to ModuloFuncionalidadeAcao
	@OneToMany(mappedBy="funcionalidadeAcao")
	private List<ModuloFuncionalidadeAcao> moduloFuncionalidadeAcaos;

	//bi-directional many-to-one association to Privilegio
	@OneToMany(mappedBy="funcionalidadeAcao")
	private List<Privilegio> privilegios;

	public FuncionalidadeAcao() {
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

	public String getConcedeAcessoA() {
		return this.concedeAcessoA;
	}

	public void setConcedeAcessoA(String concedeAcessoA) {
		this.concedeAcessoA = concedeAcessoA;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Funcionalidade getFuncionalidade() {
		return this.funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public FuncionalidadeAcao getFuncionalidadeAcao() {
		return this.funcionalidadeAcao;
	}

	public void setFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		this.funcionalidadeAcao = funcionalidadeAcao;
	}

	public List<FuncionalidadeAcao> getFuncionalidadeAcaos() {
		return this.funcionalidadeAcaos;
	}

	public void setFuncionalidadeAcaos(List<FuncionalidadeAcao> funcionalidadeAcaos) {
		this.funcionalidadeAcaos = funcionalidadeAcaos;
	}

	public FuncionalidadeAcao addFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		getFuncionalidadeAcaos().add(funcionalidadeAcao);
		funcionalidadeAcao.setFuncionalidadeAcao(this);

		return funcionalidadeAcao;
	}

	public FuncionalidadeAcao removeFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		getFuncionalidadeAcaos().remove(funcionalidadeAcao);
		funcionalidadeAcao.setFuncionalidadeAcao(null);

		return funcionalidadeAcao;
	}

	public List<ModuloFuncionalidadeAcao> getModuloFuncionalidadeAcaos() {
		return this.moduloFuncionalidadeAcaos;
	}

	public void setModuloFuncionalidadeAcaos(List<ModuloFuncionalidadeAcao> moduloFuncionalidadeAcaos) {
		this.moduloFuncionalidadeAcaos = moduloFuncionalidadeAcaos;
	}

	public ModuloFuncionalidadeAcao addModuloFuncionalidadeAcao(ModuloFuncionalidadeAcao moduloFuncionalidadeAcao) {
		getModuloFuncionalidadeAcaos().add(moduloFuncionalidadeAcao);
		moduloFuncionalidadeAcao.setFuncionalidadeAcao(this);

		return moduloFuncionalidadeAcao;
	}

	public ModuloFuncionalidadeAcao removeModuloFuncionalidadeAcao(ModuloFuncionalidadeAcao moduloFuncionalidadeAcao) {
		getModuloFuncionalidadeAcaos().remove(moduloFuncionalidadeAcao);
		moduloFuncionalidadeAcao.setFuncionalidadeAcao(null);

		return moduloFuncionalidadeAcao;
	}

	public List<Privilegio> getPrivilegios() {
		return this.privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	public Privilegio addPrivilegio(Privilegio privilegio) {
		getPrivilegios().add(privilegio);
		privilegio.setFuncionalidadeAcao(this);

		return privilegio;
	}

	public Privilegio removePrivilegio(Privilegio privilegio) {
		getPrivilegios().remove(privilegio);
		privilegio.setFuncionalidadeAcao(null);

		return privilegio;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;

import javax.persistence.Column;
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
 * The persistent class for the modulo_funcionalidade_acao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="modulo_funcionalidade_acao")
@NamedQuery(name="ModuloFuncionalidadeAcao.findAll", query="SELECT m FROM ModuloFuncionalidadeAcao m")
public class ModuloFuncionalidadeAcao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	@Column(name="exibir_menu_principal")
	private String exibirMenuPrincipal;

	@Column(name="grupo_menu")
	private String grupoMenu;

	private int ordem;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@ManyToOne
	@JoinColumn(name="funcionalidade_acao_id")
	private FuncionalidadeAcao funcionalidadeAcao;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	private Modulo modulo;

	public ModuloFuncionalidadeAcao() {
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

	public String getExibirMenuPrincipal() {
		return this.exibirMenuPrincipal;
	}

	public void setExibirMenuPrincipal(String exibirMenuPrincipal) {
		this.exibirMenuPrincipal = exibirMenuPrincipal;
	}

	public String getGrupoMenu() {
		return this.grupoMenu;
	}

	public void setGrupoMenu(String grupoMenu) {
		this.grupoMenu = grupoMenu;
	}

	public int getOrdem() {
		return this.ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public FuncionalidadeAcao getFuncionalidadeAcao() {
		return this.funcionalidadeAcao;
	}

	public void setFuncionalidadeAcao(FuncionalidadeAcao funcionalidadeAcao) {
		this.funcionalidadeAcao = funcionalidadeAcao;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

}
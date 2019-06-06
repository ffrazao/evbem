package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the modulo database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	private String codigo;

	@Lob
	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy="modulo")
	private List<Configuracao> configuracaos;

	//bi-directional many-to-one association to ModuloFuncionalidadeAcao
	@OneToMany(mappedBy="modulo")
	private List<ModuloFuncionalidadeAcao> moduloFuncionalidadeAcaos;

	public Modulo() {
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

	public List<Configuracao> getConfiguracaos() {
		return this.configuracaos;
	}

	public void setConfiguracaos(List<Configuracao> configuracaos) {
		this.configuracaos = configuracaos;
	}

	public Configuracao addConfiguracao(Configuracao configuracao) {
		getConfiguracaos().add(configuracao);
		configuracao.setModulo(this);

		return configuracao;
	}

	public Configuracao removeConfiguracao(Configuracao configuracao) {
		getConfiguracaos().remove(configuracao);
		configuracao.setModulo(null);

		return configuracao;
	}

	public List<ModuloFuncionalidadeAcao> getModuloFuncionalidadeAcaos() {
		return this.moduloFuncionalidadeAcaos;
	}

	public void setModuloFuncionalidadeAcaos(List<ModuloFuncionalidadeAcao> moduloFuncionalidadeAcaos) {
		this.moduloFuncionalidadeAcaos = moduloFuncionalidadeAcaos;
	}

	public ModuloFuncionalidadeAcao addModuloFuncionalidadeAcao(ModuloFuncionalidadeAcao moduloFuncionalidadeAcao) {
		getModuloFuncionalidadeAcaos().add(moduloFuncionalidadeAcao);
		moduloFuncionalidadeAcao.setModulo(this);

		return moduloFuncionalidadeAcao;
	}

	public ModuloFuncionalidadeAcao removeModuloFuncionalidadeAcao(ModuloFuncionalidadeAcao moduloFuncionalidadeAcao) {
		getModuloFuncionalidadeAcaos().remove(moduloFuncionalidadeAcao);
		moduloFuncionalidadeAcao.setModulo(null);

		return moduloFuncionalidadeAcao;
	}

}
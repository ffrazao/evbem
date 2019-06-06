package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the configuracao database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Configuracao.findAll", query="SELECT c FROM Configuracao c")
public class Configuracao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	@Column(name="atualizado_em")
	private Timestamp atualizadoEm;

	private String codigo;

	@Column(name="criado_em")
	private Timestamp criadoEm;

	@Lob
	private String descricao;

	private String nome;

	private String valor;

	//bi-directional many-to-one association to Configuracao
	@ManyToOne
	@JoinColumn(name="pai_id")
	private Configuracao configuracao;

	//bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy="configuracao")
	private List<Configuracao> configuracaos;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	private Modulo modulo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="criado_usuario_id")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="atualizado_usuario_id")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario3;

	public Configuracao() {
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

	public Timestamp getAtualizadoEm() {
		return this.atualizadoEm;
	}

	public void setAtualizadoEm(Timestamp atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Timestamp getCriadoEm() {
		return this.criadoEm;
	}

	public void setCriadoEm(Timestamp criadoEm) {
		this.criadoEm = criadoEm;
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Configuracao getConfiguracao() {
		return this.configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	public List<Configuracao> getConfiguracaos() {
		return this.configuracaos;
	}

	public void setConfiguracaos(List<Configuracao> configuracaos) {
		this.configuracaos = configuracaos;
	}

	public Configuracao addConfiguracao(Configuracao configuracao) {
		getConfiguracaos().add(configuracao);
		configuracao.setConfiguracao(this);

		return configuracao;
	}

	public Configuracao removeConfiguracao(Configuracao configuracao) {
		getConfiguracaos().remove(configuracao);
		configuracao.setConfiguracao(null);

		return configuracao;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

}
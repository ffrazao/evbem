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
 * The persistent class for the forma_autenticacao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="forma_autenticacao")
@NamedQuery(name="FormaAutenticacao.findAll", query="SELECT f FROM FormaAutenticacao f")
public class FormaAutenticacao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	private String codigo;

	private String config;

	@Lob
	private String descricao;

	private String nome;

	private int ordem;

	private String padrao;

	private String tipo;

	//bi-directional many-to-one association to UsuarioFormaAutenticacao
	@OneToMany(mappedBy="formaAutenticacao")
	private List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaos;

	public FormaAutenticacao() {
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

	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
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

	public String getPadrao() {
		return this.padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<UsuarioFormaAutenticacao> getUsuarioFormaAutenticacaos() {
		return this.usuarioFormaAutenticacaos;
	}

	public void setUsuarioFormaAutenticacaos(List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaos) {
		this.usuarioFormaAutenticacaos = usuarioFormaAutenticacaos;
	}

	public UsuarioFormaAutenticacao addUsuarioFormaAutenticacao(UsuarioFormaAutenticacao usuarioFormaAutenticacao) {
		getUsuarioFormaAutenticacaos().add(usuarioFormaAutenticacao);
		usuarioFormaAutenticacao.setFormaAutenticacao(this);

		return usuarioFormaAutenticacao;
	}

	public UsuarioFormaAutenticacao removeUsuarioFormaAutenticacao(UsuarioFormaAutenticacao usuarioFormaAutenticacao) {
		getUsuarioFormaAutenticacaos().remove(usuarioFormaAutenticacao);
		usuarioFormaAutenticacao.setFormaAutenticacao(null);

		return usuarioFormaAutenticacao;
	}

}
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
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	@Column(name="atualizado_em")
	private Timestamp atualizadoEm;

	@Column(name="criado_em")
	private Timestamp criadoEm;

	private String email;

	@Lob
	private byte[] foto;

	private String login;

	private String nome;

	@Column(name="pessoa_id")
	private int pessoaId;

	private String senha;

	private String tipo;

	//bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy="usuario1")
	private List<Configuracao> configuracaos1;

	//bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy="usuario2")
	private List<Configuracao> configuracaos2;

	//bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy="usuario3")
	private List<Configuracao> configuracaos3;

	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="usuario")
	private List<Token> tokens;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="criado_usuario_id")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario1")
	private List<Usuario> usuarios1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="atualizado_usuario_id")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario2")
	private List<Usuario> usuarios2;

	//bi-directional many-to-one association to UsuarioPerfil
	@ManyToOne
	@JoinColumn(name="ultimo_usuario_perfil_id")
	private UsuarioPerfil usuarioPerfil;

	//bi-directional many-to-one association to UsuarioFormaAutenticacao
	@OneToMany(mappedBy="usuario")
	private List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaos;

	//bi-directional many-to-one association to UsuarioPerfil
	@OneToMany(mappedBy="usuario")
	private List<UsuarioPerfil> usuarioPerfils;

	public Usuario() {
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

	public Timestamp getCriadoEm() {
		return this.criadoEm;
	}

	public void setCriadoEm(Timestamp criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPessoaId() {
		return this.pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Configuracao> getConfiguracaos1() {
		return this.configuracaos1;
	}

	public void setConfiguracaos1(List<Configuracao> configuracaos1) {
		this.configuracaos1 = configuracaos1;
	}

	public Configuracao addConfiguracaos1(Configuracao configuracaos1) {
		getConfiguracaos1().add(configuracaos1);
		configuracaos1.setUsuario1(this);

		return configuracaos1;
	}

	public Configuracao removeConfiguracaos1(Configuracao configuracaos1) {
		getConfiguracaos1().remove(configuracaos1);
		configuracaos1.setUsuario1(null);

		return configuracaos1;
	}

	public List<Configuracao> getConfiguracaos2() {
		return this.configuracaos2;
	}

	public void setConfiguracaos2(List<Configuracao> configuracaos2) {
		this.configuracaos2 = configuracaos2;
	}

	public Configuracao addConfiguracaos2(Configuracao configuracaos2) {
		getConfiguracaos2().add(configuracaos2);
		configuracaos2.setUsuario2(this);

		return configuracaos2;
	}

	public Configuracao removeConfiguracaos2(Configuracao configuracaos2) {
		getConfiguracaos2().remove(configuracaos2);
		configuracaos2.setUsuario2(null);

		return configuracaos2;
	}

	public List<Configuracao> getConfiguracaos3() {
		return this.configuracaos3;
	}

	public void setConfiguracaos3(List<Configuracao> configuracaos3) {
		this.configuracaos3 = configuracaos3;
	}

	public Configuracao addConfiguracaos3(Configuracao configuracaos3) {
		getConfiguracaos3().add(configuracaos3);
		configuracaos3.setUsuario3(this);

		return configuracaos3;
	}

	public Configuracao removeConfiguracaos3(Configuracao configuracaos3) {
		getConfiguracaos3().remove(configuracaos3);
		configuracaos3.setUsuario3(null);

		return configuracaos3;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setUsuario(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setUsuario(null);

		return token;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public List<Usuario> getUsuarios1() {
		return this.usuarios1;
	}

	public void setUsuarios1(List<Usuario> usuarios1) {
		this.usuarios1 = usuarios1;
	}

	public Usuario addUsuarios1(Usuario usuarios1) {
		getUsuarios1().add(usuarios1);
		usuarios1.setUsuario1(this);

		return usuarios1;
	}

	public Usuario removeUsuarios1(Usuario usuarios1) {
		getUsuarios1().remove(usuarios1);
		usuarios1.setUsuario1(null);

		return usuarios1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Usuario> getUsuarios2() {
		return this.usuarios2;
	}

	public void setUsuarios2(List<Usuario> usuarios2) {
		this.usuarios2 = usuarios2;
	}

	public Usuario addUsuarios2(Usuario usuarios2) {
		getUsuarios2().add(usuarios2);
		usuarios2.setUsuario2(this);

		return usuarios2;
	}

	public Usuario removeUsuarios2(Usuario usuarios2) {
		getUsuarios2().remove(usuarios2);
		usuarios2.setUsuario2(null);

		return usuarios2;
	}

	public UsuarioPerfil getUsuarioPerfil() {
		return this.usuarioPerfil;
	}

	public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}

	public List<UsuarioFormaAutenticacao> getUsuarioFormaAutenticacaos() {
		return this.usuarioFormaAutenticacaos;
	}

	public void setUsuarioFormaAutenticacaos(List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaos) {
		this.usuarioFormaAutenticacaos = usuarioFormaAutenticacaos;
	}

	public UsuarioFormaAutenticacao addUsuarioFormaAutenticacao(UsuarioFormaAutenticacao usuarioFormaAutenticacao) {
		getUsuarioFormaAutenticacaos().add(usuarioFormaAutenticacao);
		usuarioFormaAutenticacao.setUsuario(this);

		return usuarioFormaAutenticacao;
	}

	public UsuarioFormaAutenticacao removeUsuarioFormaAutenticacao(UsuarioFormaAutenticacao usuarioFormaAutenticacao) {
		getUsuarioFormaAutenticacaos().remove(usuarioFormaAutenticacao);
		usuarioFormaAutenticacao.setUsuario(null);

		return usuarioFormaAutenticacao;
	}

	public List<UsuarioPerfil> getUsuarioPerfils() {
		return this.usuarioPerfils;
	}

	public void setUsuarioPerfils(List<UsuarioPerfil> usuarioPerfils) {
		this.usuarioPerfils = usuarioPerfils;
	}

	public UsuarioPerfil addUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		getUsuarioPerfils().add(usuarioPerfil);
		usuarioPerfil.setUsuario(this);

		return usuarioPerfil;
	}

	public UsuarioPerfil removeUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		getUsuarioPerfils().remove(usuarioPerfil);
		usuarioPerfil.setUsuario(null);

		return usuarioPerfil;
	}

}
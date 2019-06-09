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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Usuario extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	@Column(name = "atualizado_em")
	private Timestamp atualizadoEm;

	// bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy = "usuario1")
	private List<Configuracao> configuracaos1;

	// bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy = "usuario2")
	private List<Configuracao> configuracaos2;

	// bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy = "usuario3")
	private List<Configuracao> configuracaos3;

	@Column(name = "criado_em")
	private Timestamp criadoEm;

	private String email;

	@Lob
	private byte[] foto;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String login;

	private String nome;

	@Column(name = "pessoa_id")
	private int pessoaId;

	private String senha;

	private String tipo;

	// bi-directional many-to-one association to Token
	@OneToMany(mappedBy = "usuario")
	private List<Token> tokens;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "criado_usuario_id")
	private Usuario usuario1;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "atualizado_usuario_id")
	private Usuario usuario2;

	// bi-directional many-to-one association to UsuarioFormaAutenticacao
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaos;

	// bi-directional many-to-one association to UsuarioPerfil
	@ManyToOne
	@JoinColumn(name = "ultimo_usuario_perfil_id")
	private UsuarioPerfil usuarioPerfil;

	// bi-directional many-to-one association to UsuarioPerfil
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioPerfil> usuarioPerfils;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "usuario1")
	private List<Usuario> usuarios1;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "usuario2")
	private List<Usuario> usuarios2;

}
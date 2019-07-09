package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.UsuarioTipo;
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

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@Column(name = "atualizado_em", insertable = false, updatable = false)
	private Timestamp atualizadoEm;

	@Column(name = "criado_em", insertable = false, updatable = false)
	private Timestamp criadoEm;

	private String email;

	@Lob
	private byte[] foto;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String login;

	private String nome;

	@Column(name = "pessoa_id")
	private Integer pessoaId;

	@Enumerated(EnumType.STRING)
	private UsuarioTipo tipo;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "criado_usuario_id", updatable = false)
	private Usuario criadoUsuario;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "atualizado_usuario_id")
	private Usuario atualizadoUsuario;

	// bi-directional many-to-one association to UsuarioFormaAutenticacao
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaoList;

	// bi-directional many-to-one association to UsuarioPerfil
	@ManyToOne
	@JoinColumn(name = "ultimo_usuario_perfil_id")
	private UsuarioPerfil usuarioPerfil;

	// bi-directional many-to-one association to UsuarioPerfil
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioPerfil> usuarioPerfilList;

}
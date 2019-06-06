package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the usuario_perfil database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="usuario_perfil")
@NamedQuery(name="UsuarioPerfil.findAll", query="SELECT u FROM UsuarioPerfil u")
public class UsuarioPerfil extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	private String padrao;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuarioPerfil")
	private List<Usuario> usuarios;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	private Perfil perfil;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public UsuarioPerfil() {
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

	public String getPadrao() {
		return this.padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setUsuarioPerfil(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setUsuarioPerfil(null);

		return usuario;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
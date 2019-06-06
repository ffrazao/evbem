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
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String administrador;

	private String ativo;

	private String codigo;

	@Lob
	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Privilegio
	@OneToMany(mappedBy="perfil")
	private List<Privilegio> privilegios;

	//bi-directional many-to-one association to UsuarioPerfil
	@OneToMany(mappedBy="perfil")
	private List<UsuarioPerfil> usuarioPerfils;

	public Perfil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
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

	public List<Privilegio> getPrivilegios() {
		return this.privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	public Privilegio addPrivilegio(Privilegio privilegio) {
		getPrivilegios().add(privilegio);
		privilegio.setPerfil(this);

		return privilegio;
	}

	public Privilegio removePrivilegio(Privilegio privilegio) {
		getPrivilegios().remove(privilegio);
		privilegio.setPerfil(null);

		return privilegio;
	}

	public List<UsuarioPerfil> getUsuarioPerfils() {
		return this.usuarioPerfils;
	}

	public void setUsuarioPerfils(List<UsuarioPerfil> usuarioPerfils) {
		this.usuarioPerfils = usuarioPerfils;
	}

	public UsuarioPerfil addUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		getUsuarioPerfils().add(usuarioPerfil);
		usuarioPerfil.setPerfil(this);

		return usuarioPerfil;
	}

	public UsuarioPerfil removeUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
		getUsuarioPerfils().remove(usuarioPerfil);
		usuarioPerfil.setPerfil(null);

		return usuarioPerfil;
	}

}
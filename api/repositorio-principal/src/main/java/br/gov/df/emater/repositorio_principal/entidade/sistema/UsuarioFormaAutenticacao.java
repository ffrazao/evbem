package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;

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
 * The persistent class for the usuario_forma_autenticacao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="usuario_forma_autenticacao")
@NamedQuery(name="UsuarioFormaAutenticacao.findAll", query="SELECT u FROM UsuarioFormaAutenticacao u")
public class UsuarioFormaAutenticacao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	private String valor;

	//bi-directional many-to-one association to FormaAutenticacao
	@ManyToOne
	@JoinColumn(name="forma_autenticacao_id")
	private FormaAutenticacao formaAutenticacao;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public UsuarioFormaAutenticacao() {
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public FormaAutenticacao getFormaAutenticacao() {
		return this.formaAutenticacao;
	}

	public void setFormaAutenticacao(FormaAutenticacao formaAutenticacao) {
		this.formaAutenticacao = formaAutenticacao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@NamedQuery(name="Token.findAll", query="SELECT t FROM Token t")
public class Token extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="criado_em")
	private Timestamp criadoEm;

	private String detalhe;

	@Column(name="expira_em")
	private int expiraEm;

	@Column(name="invalidado_em")
	private Timestamp invalidadoEm;

	private String tipo;

	@Lob
	private String token;

	//bi-directional many-to-one association to HistoricoAtividade
	@OneToMany(mappedBy="token")
	private List<HistoricoAtividade> historicoAtividades;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public Token() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCriadoEm() {
		return this.criadoEm;
	}

	public void setCriadoEm(Timestamp criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getDetalhe() {
		return this.detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public int getExpiraEm() {
		return this.expiraEm;
	}

	public void setExpiraEm(int expiraEm) {
		this.expiraEm = expiraEm;
	}

	public Timestamp getInvalidadoEm() {
		return this.invalidadoEm;
	}

	public void setInvalidadoEm(Timestamp invalidadoEm) {
		this.invalidadoEm = invalidadoEm;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<HistoricoAtividade> getHistoricoAtividades() {
		return this.historicoAtividades;
	}

	public void setHistoricoAtividades(List<HistoricoAtividade> historicoAtividades) {
		this.historicoAtividades = historicoAtividades;
	}

	public HistoricoAtividade addHistoricoAtividade(HistoricoAtividade historicoAtividade) {
		getHistoricoAtividades().add(historicoAtividade);
		historicoAtividade.setToken(this);

		return historicoAtividade;
	}

	public HistoricoAtividade removeHistoricoAtividade(HistoricoAtividade historicoAtividade) {
		getHistoricoAtividades().remove(historicoAtividade);
		historicoAtividade.setToken(null);

		return historicoAtividade;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
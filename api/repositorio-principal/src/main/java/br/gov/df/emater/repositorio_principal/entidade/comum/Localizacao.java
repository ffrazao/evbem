package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the localizacao database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@NamedQuery(name="Localizacao.findAll", query="SELECT l FROM Localizacao l")
public class Localizacao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nome;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="localizacao")
	private List<Endereco> enderecos;

	//bi-directional many-to-one association to Localizacao
	@ManyToOne
	@JoinColumn(name="pai_id")
	private Localizacao localizacao;

	//bi-directional many-to-one association to Localizacao
	@OneToMany(mappedBy="localizacao")
	private List<Localizacao> localizacaos;

	//bi-directional many-to-one association to LocalizacaoTipo
	@ManyToOne
	@JoinColumn(name="localizacao_tipo_id")
	private LocalizacaoTipo localizacaoTipo;

	//bi-directional one-to-one association to ReferenciaEspacial
	@OneToOne
	@JoinColumn(name="id")
	private ReferenciaEspacial referenciaEspacial;

	public Localizacao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setLocalizacao(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setLocalizacao(null);

		return endereco;
	}

	public Localizacao getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public List<Localizacao> getLocalizacaos() {
		return this.localizacaos;
	}

	public void setLocalizacaos(List<Localizacao> localizacaos) {
		this.localizacaos = localizacaos;
	}

	public Localizacao addLocalizacao(Localizacao localizacao) {
		getLocalizacaos().add(localizacao);
		localizacao.setLocalizacao(this);

		return localizacao;
	}

	public Localizacao removeLocalizacao(Localizacao localizacao) {
		getLocalizacaos().remove(localizacao);
		localizacao.setLocalizacao(null);

		return localizacao;
	}

	public LocalizacaoTipo getLocalizacaoTipo() {
		return this.localizacaoTipo;
	}

	public void setLocalizacaoTipo(LocalizacaoTipo localizacaoTipo) {
		this.localizacaoTipo = localizacaoTipo;
	}

	public ReferenciaEspacial getReferenciaEspacial() {
		return this.referenciaEspacial;
	}

	public void setReferenciaEspacial(ReferenciaEspacial referenciaEspacial) {
		this.referenciaEspacial = referenciaEspacial;
	}

}
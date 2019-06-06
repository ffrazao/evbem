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
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the localizacao_tipo database table.
 * 
 */
@Entity
@Table(catalog = "comum", name="localizacao_tipo")
@NamedQuery(name="LocalizacaoTipo.findAll", query="SELECT l FROM LocalizacaoTipo l")
public class LocalizacaoTipo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nome;

	//bi-directional many-to-one association to Localizacao
	@OneToMany(mappedBy="localizacaoTipo")
	private List<Localizacao> localizacaos;

	//bi-directional many-to-one association to LocalizacaoTipo
	@ManyToOne
	@JoinColumn(name="pai_id")
	private LocalizacaoTipo localizacaoTipo;

	//bi-directional many-to-one association to LocalizacaoTipo
	@OneToMany(mappedBy="localizacaoTipo")
	private List<LocalizacaoTipo> localizacaoTipos;

	public LocalizacaoTipo() {
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

	public List<Localizacao> getLocalizacaos() {
		return this.localizacaos;
	}

	public void setLocalizacaos(List<Localizacao> localizacaos) {
		this.localizacaos = localizacaos;
	}

	public Localizacao addLocalizacao(Localizacao localizacao) {
		getLocalizacaos().add(localizacao);
		localizacao.setLocalizacaoTipo(this);

		return localizacao;
	}

	public Localizacao removeLocalizacao(Localizacao localizacao) {
		getLocalizacaos().remove(localizacao);
		localizacao.setLocalizacaoTipo(null);

		return localizacao;
	}

	public LocalizacaoTipo getLocalizacaoTipo() {
		return this.localizacaoTipo;
	}

	public void setLocalizacaoTipo(LocalizacaoTipo localizacaoTipo) {
		this.localizacaoTipo = localizacaoTipo;
	}

	public List<LocalizacaoTipo> getLocalizacaoTipos() {
		return this.localizacaoTipos;
	}

	public void setLocalizacaoTipos(List<LocalizacaoTipo> localizacaoTipos) {
		this.localizacaoTipos = localizacaoTipos;
	}

	public LocalizacaoTipo addLocalizacaoTipo(LocalizacaoTipo localizacaoTipo) {
		getLocalizacaoTipos().add(localizacaoTipo);
		localizacaoTipo.setLocalizacaoTipo(this);

		return localizacaoTipo;
	}

	public LocalizacaoTipo removeLocalizacaoTipo(LocalizacaoTipo localizacaoTipo) {
		getLocalizacaoTipos().remove(localizacaoTipo);
		localizacaoTipo.setLocalizacaoTipo(null);

		return localizacaoTipo;
	}

}
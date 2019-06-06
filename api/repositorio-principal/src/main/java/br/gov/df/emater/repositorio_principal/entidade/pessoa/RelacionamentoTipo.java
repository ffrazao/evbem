package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the relacionamento_tipo database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="relacionamento_tipo")
@NamedQuery(name="RelacionamentoTipo.findAll", query="SELECT r FROM RelacionamentoTipo r")
public class RelacionamentoTipo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String codigo;

	private String nome;

	private String temporal;

	//bi-directional many-to-one association to Relacionamento
	@OneToMany(mappedBy="relacionamentoTipo")
	private List<Relacionamento> relacionamentos;

	//bi-directional many-to-one association to RelacionamentoConfiguracao
	@OneToMany(mappedBy="relacionamentoTipo")
	private List<RelacionamentoConfiguracao> relacionamentoConfiguracaos;

	public RelacionamentoTipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTemporal() {
		return this.temporal;
	}

	public void setTemporal(String temporal) {
		this.temporal = temporal;
	}

	public List<Relacionamento> getRelacionamentos() {
		return this.relacionamentos;
	}

	public void setRelacionamentos(List<Relacionamento> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}

	public Relacionamento addRelacionamento(Relacionamento relacionamento) {
		getRelacionamentos().add(relacionamento);
		relacionamento.setRelacionamentoTipo(this);

		return relacionamento;
	}

	public Relacionamento removeRelacionamento(Relacionamento relacionamento) {
		getRelacionamentos().remove(relacionamento);
		relacionamento.setRelacionamentoTipo(null);

		return relacionamento;
	}

	public List<RelacionamentoConfiguracao> getRelacionamentoConfiguracaos() {
		return this.relacionamentoConfiguracaos;
	}

	public void setRelacionamentoConfiguracaos(List<RelacionamentoConfiguracao> relacionamentoConfiguracaos) {
		this.relacionamentoConfiguracaos = relacionamentoConfiguracaos;
	}

	public RelacionamentoConfiguracao addRelacionamentoConfiguracao(RelacionamentoConfiguracao relacionamentoConfiguracao) {
		getRelacionamentoConfiguracaos().add(relacionamentoConfiguracao);
		relacionamentoConfiguracao.setRelacionamentoTipo(this);

		return relacionamentoConfiguracao;
	}

	public RelacionamentoConfiguracao removeRelacionamentoConfiguracao(RelacionamentoConfiguracao relacionamentoConfiguracao) {
		getRelacionamentoConfiguracaos().remove(relacionamentoConfiguracao);
		relacionamentoConfiguracao.setRelacionamentoTipo(null);

		return relacionamentoConfiguracao;
	}

}
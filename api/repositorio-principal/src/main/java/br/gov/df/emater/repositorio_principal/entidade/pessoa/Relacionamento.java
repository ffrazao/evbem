package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the relacionamento database table.
 * 
 */
@Entity
@Table(catalog = "pessoa")
@NamedQuery(name="Relacionamento.findAll", query="SELECT r FROM Relacionamento r")
public class Relacionamento extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;

	//bi-directional many-to-one association to RelacionamentoTipo
	@ManyToOne
	@JoinColumn(name="relacionamento_tipo_id")
	private RelacionamentoTipo relacionamentoTipo;

	//bi-directional many-to-one association to RelacionamentoPessoa
	@OneToMany(mappedBy="relacionamento")
	private List<RelacionamentoPessoa> relacionamentoPessoas;

	public Relacionamento() {
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

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return this.termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public RelacionamentoTipo getRelacionamentoTipo() {
		return this.relacionamentoTipo;
	}

	public void setRelacionamentoTipo(RelacionamentoTipo relacionamentoTipo) {
		this.relacionamentoTipo = relacionamentoTipo;
	}

	public List<RelacionamentoPessoa> getRelacionamentoPessoas() {
		return this.relacionamentoPessoas;
	}

	public void setRelacionamentoPessoas(List<RelacionamentoPessoa> relacionamentoPessoas) {
		this.relacionamentoPessoas = relacionamentoPessoas;
	}

	public RelacionamentoPessoa addRelacionamentoPessoa(RelacionamentoPessoa relacionamentoPessoa) {
		getRelacionamentoPessoas().add(relacionamentoPessoa);
		relacionamentoPessoa.setRelacionamento(this);

		return relacionamentoPessoa;
	}

	public RelacionamentoPessoa removeRelacionamentoPessoa(RelacionamentoPessoa relacionamentoPessoa) {
		getRelacionamentoPessoas().remove(relacionamentoPessoa);
		relacionamentoPessoa.setRelacionamento(null);

		return relacionamentoPessoa;
	}

}
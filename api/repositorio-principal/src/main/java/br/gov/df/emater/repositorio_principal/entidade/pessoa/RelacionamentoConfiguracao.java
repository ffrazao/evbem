package br.gov.df.emater.repositorio_principal.entidade.pessoa;

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
 * The persistent class for the relacionamento_configuracao database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="relacionamento_configuracao")
@NamedQuery(name="RelacionamentoConfiguracao.findAll", query="SELECT r FROM RelacionamentoConfiguracao r")
public class RelacionamentoConfiguracao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to RelacionamentoFuncao
	@ManyToOne
	@JoinColumn(name="relacionador_funcao_id")
	private RelacionamentoFuncao relacionamentoFuncao1;

	//bi-directional many-to-one association to RelacionamentoFuncao
	@ManyToOne
	@JoinColumn(name="relacionado_funcao_id")
	private RelacionamentoFuncao relacionamentoFuncao2;

	//bi-directional many-to-one association to RelacionamentoTipo
	@ManyToOne
	@JoinColumn(name="relacionamento_tipo_id")
	private RelacionamentoTipo relacionamentoTipo;

	public RelacionamentoConfiguracao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RelacionamentoFuncao getRelacionamentoFuncao1() {
		return this.relacionamentoFuncao1;
	}

	public void setRelacionamentoFuncao1(RelacionamentoFuncao relacionamentoFuncao1) {
		this.relacionamentoFuncao1 = relacionamentoFuncao1;
	}

	public RelacionamentoFuncao getRelacionamentoFuncao2() {
		return this.relacionamentoFuncao2;
	}

	public void setRelacionamentoFuncao2(RelacionamentoFuncao relacionamentoFuncao2) {
		this.relacionamentoFuncao2 = relacionamentoFuncao2;
	}

	public RelacionamentoTipo getRelacionamentoTipo() {
		return this.relacionamentoTipo;
	}

	public void setRelacionamentoTipo(RelacionamentoTipo relacionamentoTipo) {
		this.relacionamentoTipo = relacionamentoTipo;
	}

}
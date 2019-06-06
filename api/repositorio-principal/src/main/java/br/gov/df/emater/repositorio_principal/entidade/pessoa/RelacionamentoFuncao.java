package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the relacionamento_funcao database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="relacionamento_funcao")
@NamedQuery(name="RelacionamentoFuncao.findAll", query="SELECT r FROM RelacionamentoFuncao r")
public class RelacionamentoFuncao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String codigo;

	private String nome;

	@Column(name="nome_se_feminino")
	private String nomeSeFeminino;

	//bi-directional many-to-one association to RelacionamentoConfiguracao
	@OneToMany(mappedBy="relacionamentoFuncao1")
	private List<RelacionamentoConfiguracao> relacionamentoConfiguracaos1;

	//bi-directional many-to-one association to RelacionamentoConfiguracao
	@OneToMany(mappedBy="relacionamentoFuncao2")
	private List<RelacionamentoConfiguracao> relacionamentoConfiguracaos2;

	//bi-directional many-to-one association to RelacionamentoPessoa
	@OneToMany(mappedBy="relacionamentoFuncao")
	private List<RelacionamentoPessoa> relacionamentoPessoas;

	public RelacionamentoFuncao() {
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

	public String getNomeSeFeminino() {
		return this.nomeSeFeminino;
	}

	public void setNomeSeFeminino(String nomeSeFeminino) {
		this.nomeSeFeminino = nomeSeFeminino;
	}

	public List<RelacionamentoConfiguracao> getRelacionamentoConfiguracaos1() {
		return this.relacionamentoConfiguracaos1;
	}

	public void setRelacionamentoConfiguracaos1(List<RelacionamentoConfiguracao> relacionamentoConfiguracaos1) {
		this.relacionamentoConfiguracaos1 = relacionamentoConfiguracaos1;
	}

	public RelacionamentoConfiguracao addRelacionamentoConfiguracaos1(RelacionamentoConfiguracao relacionamentoConfiguracaos1) {
		getRelacionamentoConfiguracaos1().add(relacionamentoConfiguracaos1);
		relacionamentoConfiguracaos1.setRelacionamentoFuncao1(this);

		return relacionamentoConfiguracaos1;
	}

	public RelacionamentoConfiguracao removeRelacionamentoConfiguracaos1(RelacionamentoConfiguracao relacionamentoConfiguracaos1) {
		getRelacionamentoConfiguracaos1().remove(relacionamentoConfiguracaos1);
		relacionamentoConfiguracaos1.setRelacionamentoFuncao1(null);

		return relacionamentoConfiguracaos1;
	}

	public List<RelacionamentoConfiguracao> getRelacionamentoConfiguracaos2() {
		return this.relacionamentoConfiguracaos2;
	}

	public void setRelacionamentoConfiguracaos2(List<RelacionamentoConfiguracao> relacionamentoConfiguracaos2) {
		this.relacionamentoConfiguracaos2 = relacionamentoConfiguracaos2;
	}

	public RelacionamentoConfiguracao addRelacionamentoConfiguracaos2(RelacionamentoConfiguracao relacionamentoConfiguracaos2) {
		getRelacionamentoConfiguracaos2().add(relacionamentoConfiguracaos2);
		relacionamentoConfiguracaos2.setRelacionamentoFuncao2(this);

		return relacionamentoConfiguracaos2;
	}

	public RelacionamentoConfiguracao removeRelacionamentoConfiguracaos2(RelacionamentoConfiguracao relacionamentoConfiguracaos2) {
		getRelacionamentoConfiguracaos2().remove(relacionamentoConfiguracaos2);
		relacionamentoConfiguracaos2.setRelacionamentoFuncao2(null);

		return relacionamentoConfiguracaos2;
	}

	public List<RelacionamentoPessoa> getRelacionamentoPessoas() {
		return this.relacionamentoPessoas;
	}

	public void setRelacionamentoPessoas(List<RelacionamentoPessoa> relacionamentoPessoas) {
		this.relacionamentoPessoas = relacionamentoPessoas;
	}

	public RelacionamentoPessoa addRelacionamentoPessoa(RelacionamentoPessoa relacionamentoPessoa) {
		getRelacionamentoPessoas().add(relacionamentoPessoa);
		relacionamentoPessoa.setRelacionamentoFuncao(this);

		return relacionamentoPessoa;
	}

	public RelacionamentoPessoa removeRelacionamentoPessoa(RelacionamentoPessoa relacionamentoPessoa) {
		getRelacionamentoPessoas().remove(relacionamentoPessoa);
		relacionamentoPessoa.setRelacionamentoFuncao(null);

		return relacionamentoPessoa;
	}

}
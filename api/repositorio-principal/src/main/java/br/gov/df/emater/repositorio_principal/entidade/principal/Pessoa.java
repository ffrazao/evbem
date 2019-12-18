package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.gov.df.emater.repositorio_principal.dominio.pessoa.PessoaTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Nomeavel;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaArquivo;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaEmail;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaEndereco;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaFoto;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaRelacionamento;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaTelefone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@Table(catalog = "principal")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Pessoa extends EntidadeBase implements Nomeavel {

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaArquivo> arquivoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaEmail> emailList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaEndereco> enderecoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaFoto> fotoList = new ArrayList<>();

	@Id
	private Integer id;

	private String nome;

	@Column(name = "nome_reduzido")
	private String nomeReduzido;

	@Enumerated(EnumType.STRING)
	@Column(name = "pessoa_tipo")
	private PessoaTipo pessoaTipo;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "id")
	@MapsId
	private Recurso recurso;

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaRelacionamento> relacionamentoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaTelefone> telefoneList = new ArrayList<>();

	public Pessoa(Integer valor) {
		super(valor);
	}

	@Override
	public Pessoa infoBasica() {
		Pessoa result = (Pessoa) super.infoBasica();
		result.setArquivoList(result.getArquivoList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		result.setEmailList(result.getEmailList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		result.setEnderecoList(result.getEnderecoList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		result.setFotoList(result.getFotoList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		result.setRelacionamentoList(
				result.getRelacionamentoList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		result.setTelefoneList(result.getTelefoneList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		if (result.getRecurso() != null) {
			result.setRecurso(result.getRecurso().infoBasica());
		}
		return result;
	}

}
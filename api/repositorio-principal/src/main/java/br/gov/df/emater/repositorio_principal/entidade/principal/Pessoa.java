package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
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
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Pessoa extends EntidadeBase implements Serializable, Identificavel, Nomeavel {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nome;

	@Column(name = "nome_reduzido")
	private String nomeReduzido;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaArquivo> pessoaArquivoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaEmail> pessoaEmailList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaEndereco> pessoaEnderecoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaFoto> pessoaFotoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaRelacionamento> pessoaRelacionamentoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaTelefone> pessoaTelefoneList = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "pessoa_tipo")
	private PessoaTipo pessoaTipo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	private Recurso recurso;

}
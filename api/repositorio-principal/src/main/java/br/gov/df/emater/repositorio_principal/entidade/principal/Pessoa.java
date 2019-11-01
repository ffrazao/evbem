package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.df.emater.repositorio_principal.dominio.pessoa.PessoaTipo;
import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.Nomeavel;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaArquivo;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaEmail;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaEndereco;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaFoto;
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
public class Pessoa extends EntidadeBase implements Serializable, Nomeavel {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String nome;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaArquivo> pessoaArquivoList;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaEmail> pessoaEmailList;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaEndereco> pessoaEnderecoList;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaFoto> pessoaFotoList;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<PessoaTelefone> pessoaTelefoneList;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private PessoaTipo tipo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	@MapsId
	@JsonIgnore	
	private Recurso recurso;

}
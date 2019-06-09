package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the configuracao database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Configuracao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	@Column(name = "atualizado_em")
	private Timestamp atualizadoEm;

	private String codigo;

	// bi-directional many-to-one association to Configuracao
	@ManyToOne
	@JoinColumn(name = "pai_id")
	private Configuracao configuracao;

	// bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy = "configuracao")
	private List<Configuracao> configuracaos;

	@Column(name = "criado_em")
	private Timestamp criadoEm;

	@Lob
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to Modulo
	@ManyToOne
	private Modulo modulo;

	private String nome;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "criado_usuario_id")
	private Usuario usuario1;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "atualizado_usuario_id")
	private Usuario usuario2;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario3;

	private String valor;

}
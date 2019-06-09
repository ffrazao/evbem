package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Endereco extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cep;

	private String complemento;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to Localizacao
	@ManyToOne
	private Localizacao localizacao;

	private String logradouro;

	private String numero;

	@Lob
	@Column(name = "ponto_referencia")
	private String pontoReferencia;

	// bi-directional one-to-one association to ReferenciaEspacial
	@OneToOne
	@JoinColumn(name = "id")
	private ReferenciaEspacial referenciaEspacial;

}
package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the referencia_espacial database table.
 * 
 */
@Entity
@Table(catalog = "comum", name = "referencia_espacial")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReferenciaEspacial extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String area;

	// bi-directional one-to-one association to Endereco
	@OneToOne(mappedBy = "referenciaEspacial")
	private Endereco endereco;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional one-to-one association to Localizacao
	@OneToOne(mappedBy = "referenciaEspacial")
	private Localizacao localizacao;

	private String tipo;

}
package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the pessoa_juridica database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "pessoa_juridica")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaJuridica extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cnpj;

	@Temporal(TemporalType.DATE)
	private Date encerramento;

	@Temporal(TemporalType.DATE)
	private Date fundacao;

}
package br.gov.df.emater.repositorio_principal.entidade.funcional;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaJuridica;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Empregador database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Empregador extends PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;

}

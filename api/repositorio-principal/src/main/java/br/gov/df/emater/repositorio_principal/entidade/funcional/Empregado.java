package br.gov.df.emater.repositorio_principal.entidade.funcional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaFisica;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Empregado database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@DiscriminatorValue("Empregado")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Empregado extends PessoaFisica {

	public Empregado(Integer valor) {
		super(valor);
	}

	@Override
	public Empregado infoBasica() {
		Empregado result = (Empregado) super.infoBasica();
		return result;
	}

}

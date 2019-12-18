package br.gov.df.emater.repositorio_principal.entidade.funcional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@DiscriminatorValue("Empregador")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Empregador extends PessoaJuridica {

	public Empregador(Integer valor) {
		super(valor);
	}

	@Override
	public Empregador infoBasica() {
		Empregador result = (Empregador) super.infoBasica();
		return result;
	}

}

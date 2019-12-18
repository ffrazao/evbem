package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.NomeavelCodificavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the relacionamento_tipo database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "relacionamento_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class RelacionamentoTipo extends EntidadeBase implements NomeavelCodificavel {

	private String codigo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private Confirmacao temporal;

	public RelacionamentoTipo(Integer valor) {
		super(valor);
	}

	@Override
	public RelacionamentoTipo infoBasica() {
		RelacionamentoTipo result = (RelacionamentoTipo) super.infoBasica();
		return result;
	}

}
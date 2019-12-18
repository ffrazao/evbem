package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.funcional.UnidadeOrganizacional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Infracao database table.
 * 
 */
@Entity
@Table(catalog = "veiculo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Infracao extends VeiculoEvento {

	@Column(name = "aviso_condutor")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar avisoCondutor;

	@Column(name = "envio_email")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar envioEmail;

	@Column(name = "limite_justificativa")
	@Temporal(TemporalType.DATE)
	private Calendar limiteJustificativa;

	@ManyToOne
	@JoinColumn(name = "lotacao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeOrganizacional lotacao;

	@Column(name = "notificacao")
	@Temporal(TemporalType.DATE)
	private Calendar notificacao;

	@ManyToOne
	@JoinColumn(name = "orgao_autuador_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private OrgaoTransito orgaoAutuador;

	@Column(name = "pagamento")
	@Temporal(TemporalType.DATE)
	private Calendar pagamento;

	@Column(name = "recebimento_boleto")
	@Temporal(TemporalType.DATE)
	private Calendar recebimentoBoleto;

	private BigDecimal valor;

	@Column(name = "vencimento")
	@Temporal(TemporalType.DATE)
	private Calendar vencimento;

	public Infracao(Integer valor) {
		super(valor);
	}

	@Override
	public Infracao infoBasica() {
		Infracao result = (Infracao) super.infoBasica();
		if (result.getLotacao() != null) {
			result.setLotacao(result.getLotacao().infoBasica());
		}
		if (result.getOrgaoAutuador() != null) {
			result.setOrgaoAutuador(result.getOrgaoAutuador().infoBasica());
		}
		return result;
	}

}

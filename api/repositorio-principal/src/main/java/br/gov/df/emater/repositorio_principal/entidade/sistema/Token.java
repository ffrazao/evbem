package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.conversor.JsonHashMapConverter;
import br.gov.df.emater.repositorio_principal.dominio.TokenTipo;
import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.Identificavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Token extends EntidadeBase implements Serializable, Identificavel {

	private static final long serialVersionUID = 1L;

	@Column(name = "criado_em")
	private Timestamp criadoEm;

	@Lob
	@Convert(converter = JsonHashMapConverter.class)
	private Map<String, Object> detalhe;

	@Column(name = "expira_em")
	private Integer expiraEm;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "invalidado_em")
	private Timestamp invalidadoEm;

	@Enumerated(EnumType.STRING)
	private TokenTipo tipo;

	@Lob
	private String token;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}
package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.conversor.JsonHashMapConverter;
import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.FormaAutenticacaoTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.NomeavelCodificavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Ordenavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the forma_autenticacao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "forma_autenticacao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class FormaAutenticacao extends EntidadeBase implements NomeavelCodificavel, Ativavel, Ordenavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	private String codigo;

	@Lob
	@Convert(converter = JsonHashMapConverter.class)
	private Map<String, Object> config;

	@Lob
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Integer ordem;

	@Enumerated(EnumType.STRING)
	private Confirmacao padrao;

	@Enumerated(EnumType.STRING)
	private FormaAutenticacaoTipo tipo;

	public FormaAutenticacao(Integer valor) {
		super(valor);
	}

	@Override
	public FormaAutenticacao infoBasica() {
		FormaAutenticacao result = (FormaAutenticacao) super.infoBasica();
		return result;
	}

}
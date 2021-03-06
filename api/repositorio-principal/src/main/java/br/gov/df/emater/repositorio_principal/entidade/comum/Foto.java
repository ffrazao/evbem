package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.df.emater.repositorio_principal.conversor.PointJsonDeserializer;
import br.gov.df.emater.repositorio_principal.conversor.PointJsonSerializer;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Nomeavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the foto database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Foto extends EntidadeBase implements Nomeavel {

	@Column(name = "altura_pixel")
	private Integer alturaPixel;

	private String autores;

	@Lob
	private byte[] conteudo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Lob
	private String descricao;

	private String extensao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "largura_pixel")
	private Integer larguraPixel;

	@JsonSerialize(using = PointJsonSerializer.class)
	@JsonDeserialize(using = PointJsonDeserializer.class)
	private Point local;

	private String md5;

	private String nome;

	@Column(name = "resolucao_horizontal_dpi")
	private Integer resolucaoHorizontalDpi;

	@Column(name = "resolucao_vertical_dpi")
	private Integer resolucaoVerticalDpi;

	@Column(name = "tamanho_bytes")
	private Integer tamanhoBytes;

	@Override
	public Foto infoBasica() {
		return (Foto) super.infoBasica();
	}

}
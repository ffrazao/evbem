package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;
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

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
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
@EqualsAndHashCode(callSuper = false)
public class Foto extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "altura_pixel")
	private int alturaPixel;

	private String autores;

	@Lob
	private byte[] conteudo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Lob
	private String descricao;

	private String extensao;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "largura_pixel")
	private int larguraPixel;

	private Point local;

	private String md5;

	private String nome;

	@Column(name = "resolucao_horizontal_dpi")
	private int resolucaoHorizontalDpi;

	@Column(name = "resolucao_vertical_dpi")
	private int resolucaoVerticalDpi;

	@Column(name = "tamanho_bytes")
	private int tamanhoBytes;

}
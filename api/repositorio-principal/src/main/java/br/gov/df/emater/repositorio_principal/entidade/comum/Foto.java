package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.geo.Point;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the foto database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@NamedQuery(name="Foto.findAll", query="SELECT f FROM Foto f")
public class Foto extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="altura_pixel")
	private int alturaPixel;

	private String autores;

	@Lob
	private byte[] conteudo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Lob
	private String descricao;

	private String extensao;

	@Column(name="largura_pixel")
	private int larguraPixel;

	private Point local;

	private String md5;

	private String nome;

	@Column(name="resolucao_horizontal_dpi")
	private int resolucaoHorizontalDpi;

	@Column(name="resolucao_vertical_dpi")
	private int resolucaoVerticalDpi;

	@Column(name="tamanho_bytes")
	private int tamanhoBytes;

	public Foto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlturaPixel() {
		return this.alturaPixel;
	}

	public void setAlturaPixel(int alturaPixel) {
		this.alturaPixel = alturaPixel;
	}

	public String getAutores() {
		return this.autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public byte[] getConteudo() {
		return this.conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getExtensao() {
		return this.extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public int getLarguraPixel() {
		return this.larguraPixel;
	}

	public void setLarguraPixel(int larguraPixel) {
		this.larguraPixel = larguraPixel;
	}

	public Point getLocal() {
		return this.local;
	}

	public void setLocal(Point local) {
		this.local = local;
	}

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getResolucaoHorizontalDpi() {
		return this.resolucaoHorizontalDpi;
	}

	public void setResolucaoHorizontalDpi(int resolucaoHorizontalDpi) {
		this.resolucaoHorizontalDpi = resolucaoHorizontalDpi;
	}

	public int getResolucaoVerticalDpi() {
		return this.resolucaoVerticalDpi;
	}

	public void setResolucaoVerticalDpi(int resolucaoVerticalDpi) {
		this.resolucaoVerticalDpi = resolucaoVerticalDpi;
	}

	public int getTamanhoBytes() {
		return this.tamanhoBytes;
	}

	public void setTamanhoBytes(int tamanhoBytes) {
		this.tamanhoBytes = tamanhoBytes;
	}

}
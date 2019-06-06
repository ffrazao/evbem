package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@Table(catalog = "produto")
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nome;

	//bi-directional many-to-one association to Modelo
	@OneToMany(mappedBy="marca")
	private List<Modelo> modelos;

	public Marca() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Modelo> getModelos() {
		return this.modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo addModelo(Modelo modelo) {
		getModelos().add(modelo);
		modelo.setMarca(this);

		return modelo;
	}

	public Modelo removeModelo(Modelo modelo) {
		getModelos().remove(modelo);
		modelo.setMarca(null);

		return modelo;
	}

}
package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;


/**
 * The persistent class for the telefone database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@NamedQuery(name="Telefone.findAll", query="SELECT t FROM Telefone t")
@Data
public class Telefone extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String numero;

	public Telefone() {
		Telefone t = new Telefone();
		t.setNumero();
	}

}
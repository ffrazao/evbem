package br.gov.df.emater.repositorio_principal.entidade.comum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeUnica;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@EntidadeUnica({ "usuario", "dominio" })
public class Email extends EntidadeBase {

	private String dominio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String usuario;

	public Email(Integer valor) {
		super(valor);
	}

	@Transient
	public String getEmail() {
		return StringUtils.isBlank(usuario) || StringUtils.isBlank(dominio) ? ""
				: String.format("%s@%s", usuario, dominio);
	}

	@Override
	public Email infoBasica() {
		return (Email) super.infoBasica();
	}

	@Transient
	public void setEmail(String email) {
		if (StringUtils.isBlank(email)) {
			this.usuario = null;
			this.dominio = null;
		} else {
			String[] parte = email.split("@");
			setEmail(parte[0], parte[1]);
		}
	}

	@Transient
	public void setEmail(String usuario, String dominio) {
		setUsuario(usuario);
		setDominio(dominio);
	}

}
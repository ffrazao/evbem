package br.gov.df.emater.repositorio_principal.entidade.base;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Transient;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.transporte.InfoBasica;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class EntidadeBase implements Serializable, Identificavel, InfoBasica<EntidadeBase> {

	private static final long serialVersionUID = 1L;

	public EntidadeBase(Integer valor) {
		this.setId(valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EntidadeBase)) {
			return false;
		}
		EntidadeBase other = (EntidadeBase) obj;
		return Objects.equals(getId(), other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public EntidadeBase infoBasica(Dep<?,?,?,?> dep) {
		EntidadeBase result;

		Hibernate.initialize(this);

		try {
			result = this.getClass().newInstance();

			BeanWrapper origemWrapper = new BeanWrapperImpl(this);
			BeanWrapper destinoWrapper = new BeanWrapperImpl(result);

			for (PropertyDescriptor w : origemWrapper.getPropertyDescriptors()) {
				Object valor = origemWrapper.getPropertyValue(w.getName());

//				if ("arquivoList".equalsIgnoreCase(w.getName()) || "telefoneList".equalsIgnoreCase(w.getName())
//						|| "enderecoList".equalsIgnoreCase(w.getName()) || "fotoList".equalsIgnoreCase(w.getName())
//						|| "relacionamentoList".equalsIgnoreCase(w.getName())) {
//					continue;
//				}

				if (valor instanceof Collection) {
					Optional<Dep<?, ?, ?, ?>> subDep = dep.getDependencia(w.getName());
					s
					Hibernate.initialize(valor);
					Collection<EntidadeBase> valorCol = (Collection<EntidadeBase>) valor.getClass().newInstance();
					for (EntidadeBase col : (Collection<EntidadeBase>) valor) {
						valorCol.add(col);
					}
					valor = valorCol;
				}

				if (!"class".equalsIgnoreCase(w.getName())) {
					destinoWrapper.setPropertyValue(w.getName(), valor);
				}
			}

		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}

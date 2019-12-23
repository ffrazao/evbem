package br.gov.df.emater.repositorio_principal.entidade.base;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Transient;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

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
		if (obj == null || !(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		EntidadeBase other = (EntidadeBase) obj;
		return Objects.equals(this.getId(), other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	@Transient
	public EntidadeBase infoBasica() {
		return this.infoBasica(new HashSet<>());
	}

	@SuppressWarnings("unchecked")
	@Transient
	private EntidadeBase infoBasica(Set<EntidadeBase> instanciados) {
		EntidadeBase result;
		try {
			result = this.getClass().newInstance();

			// método para evitar a recursividade infinita
			result.setId(this.getId());
			EntidadeBase existe = instanciados.stream().filter(result::equals).findAny().orElse(null);
			if (existe == null) {
				instanciados.add(result);
			} else {
				return existe;
			}

			BeanWrapper origemWrapper = new BeanWrapperImpl(this);
			BeanWrapper destinoWrapper = new BeanWrapperImpl(result);
			for (PropertyDescriptor w : origemWrapper.getPropertyDescriptors()) {
				if (Arrays.asList("id", "class").contains(w.getName().toLowerCase())) {
					continue;
				}

				Object valor = origemWrapper.getPropertyValue(w.getName());
				if (valor instanceof Collection) {
					int cont = 0;
					Collection<EntidadeBase> valorCol = (Collection<EntidadeBase>) novaCollecao(w.getPropertyType());
					for (EntidadeBase col : (Collection<EntidadeBase>) valor) {
						valorCol.add(col.infoBasica(instanciados));
						// FIXME trocar este número 3 por um mecanismo de configuração de valor
						if (++cont >= 3) {
							break;
						}
					}
					valor = valorCol;
				}
				destinoWrapper.setPropertyValue(w.getName(), valor);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> Collection<EntidadeBase> novaCollecao(Class<T> tipo) {
		if (tipo.equals(List.class)) {
			return (Collection<EntidadeBase>) new ArrayList<T>();
		} else if (tipo.equals(Set.class)) {
			return (Collection<EntidadeBase>) new HashSet<T>();
		} else {
			try {
				return (Collection<EntidadeBase>) tipo.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException("Tipo de coleção não reconhecido pelo framework!");
			}
		}
	}

}

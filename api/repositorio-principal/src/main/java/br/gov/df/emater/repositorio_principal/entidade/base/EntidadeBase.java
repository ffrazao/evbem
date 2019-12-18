package br.gov.df.emater.repositorio_principal.entidade.base;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

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
	public EntidadeBase infoBasica() {
		EntidadeBase result;
		
		try {
			result = this.getClass().newInstance();
			
			BeanWrapper origemWrapper = new BeanWrapperImpl(this);
			BeanWrapper destinoWrapper = new BeanWrapperImpl(result);
			
			for (PropertyDescriptor w: origemWrapper.getPropertyDescriptors()) {
				Object valor = origemWrapper.getPropertyValue(w.getName());
				
				if (valor instanceof Collection) {
					Collection<EntidadeBase> valorCol = (Collection<EntidadeBase>) valor.getClass().newInstance();
					for (EntidadeBase col: (Collection<EntidadeBase>) valor) {
						valorCol.add(col);
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

}

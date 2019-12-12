package br.gov.df.emater.repositorio_principal.entidade.base;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class EntidadeUnicaUtil {

	@Autowired
	private EntityManager em;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void executar(final Object entidade) {
		if (entidade == null) {
			return;
		}
		final EntidadeUnica entidadeUnica = AnnotationUtils.findAnnotation(entidade.getClass(), EntidadeUnica.class);
		if (entidadeUnica != null) {
			final BeanWrapper entidadeExemplo = new BeanWrapperImpl(BeanUtils.instantiateClass(entidade.getClass()));
			final BeanWrapper entidadeOrigem = new BeanWrapperImpl(entidade);
			Arrays.stream(entidadeUnica.value()).forEach(valorUnico -> entidadeExemplo.setPropertyValue(valorUnico,
					entidadeOrigem.getPropertyValue(valorUnico)));

			new SimpleJpaRepository(entidade.getClass(), this.em);
		}
		Class<?> clazz = entidade.getClass();
		while (!Arrays.asList(EntidadeBase.class, Object.class, Class.class, Enum.class).contains(clazz)) {
			for (final Field field : clazz.getDeclaredFields()) {
				Object valor = null;
				try {
					field.setAccessible(true);
					valor = field.get(entidade);
					if (valor instanceof Collection) {
						final Collection<?> col = ((Collection<?>) field.get(entidade));
						if (col.isEmpty()) {
							continue;
						}
						col.stream().forEach(r -> this.executar(r));
					}
					if (valor instanceof EntidadeBase) {
						this.executar(valor);
					}
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
					continue;
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

}

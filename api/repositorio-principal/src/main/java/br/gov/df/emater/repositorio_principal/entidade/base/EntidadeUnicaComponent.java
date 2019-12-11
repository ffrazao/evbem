package br.gov.df.emater.repositorio_principal.entidade.base;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class EntidadeUnicaComponent {

	@Autowired
	private EntityManager em;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void executar(Object entidade) {
		if (entidade == null) {
			return;
		}
		EntidadeUnica entidadeUnica = AnnotationUtils.findAnnotation(entidade.getClass(), EntidadeUnica.class);
		if (entidadeUnica != null) {

			// aprender a como instanciar uma classe generica
			try {
				Object entidadeExemplo = entidade.getClass().newInstance();
				for (String valorUnico : entidadeUnica.value()) {
					entidadeExemplo.getClass().getField(valorUnico).set(entidadeExemplo, entidade.getClass().getField(valorUnico).get(entidade));
				}

				Example exemplo = Example.of(entidadeExemplo, ExampleMatcher.matchingAll());

				SimpleJpaRepository<?, ?> jpa = new SimpleJpaRepository(entidade.getClass(), em);
				List resp = jpa.findAll(exemplo);

				System.out.println(resp);
				
			} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
		Class<?> clazz = entidade.getClass();
		while (!Arrays.asList(EntidadeBase.class, Object.class, Class.class, Enum.class).contains(clazz)) {
			for (Field field : clazz.getDeclaredFields()) {
				Object valor = null;
				try {
					field.setAccessible(true);
					valor = field.get(entidade);
					if (valor instanceof Collection) {
						Collection<?> col = ((Collection<?>) field.get(entidade));
						if (col.isEmpty()) {
							continue;
						}
						col.stream().forEach(r -> executar(r));
					}
					if (valor instanceof EntidadeBase) {
						executar(valor);
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

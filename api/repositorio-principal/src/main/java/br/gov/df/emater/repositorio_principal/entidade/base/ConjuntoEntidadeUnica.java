package br.gov.df.emater.repositorio_principal.entidade.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConjuntoEntidadeUnica {

	EntidadeUnica[] value() default {};

}

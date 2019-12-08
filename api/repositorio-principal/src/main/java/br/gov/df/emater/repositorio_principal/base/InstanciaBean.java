package br.gov.df.emater.repositorio_principal.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class InstanciaBean implements BeanFactoryAware {

	private BeanFactory beanFactory;

	public InstanciaBean() {
	}

	@Bean
	@Primary
	public InstanciaBean create() {
		return new InstanciaBean();
	}
	
	@Override
	public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	public <E> E instanciarBean(Class<E> c) {
		return this.beanFactory.getBean(c);
	}

}


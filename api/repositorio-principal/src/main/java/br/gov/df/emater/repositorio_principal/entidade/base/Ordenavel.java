package br.gov.df.emater.repositorio_principal.entidade.base;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public interface Ordenavel {

	public Integer getOrdem();

	default void reordenar(final Iterable<Ordenavel> ordenaveis) {
		final AtomicInteger ordem = new AtomicInteger(0);
		ordenaveis.forEach(o -> o.setOrdem(ordem.incrementAndGet()));
	}

	default void reordenar(final Ordenavel... ordenaveis) {
		this.reordenar(Arrays.asList(ordenaveis));
	}

	default Ordenavel reordenar(final Ordenavel ordenavel) {
		final Integer ordem = ordenavel.getOrdem();
		ordenavel.setOrdem(this.getOrdem());
		this.setOrdem(ordem);
		return this;
	}

	public void setOrdem(Integer ordem);

}

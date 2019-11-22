package br.gov.df.emater.repositorio_principal.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dep<E extends EntidadeBase, D extends JpaRepository<E, Integer>> {

	public static <E extends EntidadeBase, D extends JpaRepository<E, Integer>> Dep<?, ?> of(Class<E> c1, Class<D> c2,
			Dep<?, ?>... dependencias) {
		return new Dep<E, D>(c1, c2, dependencias);
	}

	private Class<D> dao;

	private Set<Dep<?, ?>> dependencias = new HashSet<>();

	private Class<E> entidade;

	public Dep(Class<E> entidade, Class<D> dao, Dep<?, ?>... dependencias) {
		this.entidade = entidade;
		this.dao = dao;
		this.dependencias.addAll(Arrays.asList(dependencias));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Dep)) {
			return false;
		}
		Dep<?, ?> other = (Dep<?, ?>) obj;
		return Objects.equals(entidade, other.entidade);
	}

	@SuppressWarnings("hiding")
	public <E extends EntidadeBase> Optional<Dep<?, ?>> getDaoDependencias(E entidade) {
		return this.dependencias.stream().filter(d -> d.equals(entidade)).findFirst();
	}

	@Override
	public int hashCode() {
		return Objects.hash(entidade);
	}

}

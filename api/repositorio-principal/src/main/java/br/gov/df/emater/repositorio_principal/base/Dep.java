package br.gov.df.emater.repositorio_principal.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.transporte.FiltroDTO;
import br.gov.df.emater.transporte.ListagemDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Dep<E extends EntidadeBase, D extends JpaRepository<E, Integer>, F extends FiltroDTO, L extends ListagemDTO> {

	public static <E extends EntidadeBase, D extends JpaRepository<E, Integer>, F extends FiltroDTO, L extends ListagemDTO> Dep<E, D, F, L> of(
			final String funcionalidadeCampo, final Class<E> entidade, final Class<D> daoClass, final Class<F> filtro,
			final Class<L> listagem, final Dep<?, ?, ?, ?>... dependencias) {
		return new Dep<>(funcionalidadeCampo, entidade, daoClass, filtro, listagem, dependencias);
	}

	public static <E extends EntidadeBase, D extends JpaRepository<E, Integer>, F extends FiltroDTO, L extends ListagemDTO> Dep<E, D, F, L> of(
			final String funcionalidadeCampo, final Class<E> entidade, final Class<D> daoClass, final Class<F> filtro,
			final Dep<?, ?, ?, ?>... dependencias) {
		return Dep.of(funcionalidadeCampo, entidade, daoClass, filtro, null, dependencias);
	}

	public static <E extends EntidadeBase, D extends JpaRepository<E, Integer>, F extends FiltroDTO, L extends ListagemDTO> Dep<E, D, F, L> of(
			final String funcionalidadeCampo, final Class<E> entidade, final Class<D> daoClass,
			final Dep<?, ?, ?, ?>... dependencias) {
		return Dep.of(funcionalidadeCampo, entidade, daoClass, null, null, dependencias);
	}

	private D dao;

	@Setter(value = AccessLevel.NONE)
	private final Class<D> daoClass;

	@Setter(value = AccessLevel.NONE)
	private final Set<Dep<?, ?, ?, ?>> dependencias = new HashSet<>();

	@Setter(value = AccessLevel.NONE)
	private final Class<E> entidade;

	@Setter(value = AccessLevel.NONE)
	private final Class<F> filtro;

	@Setter(value = AccessLevel.NONE)
	private final String funcionalidadeCampo;

	@Setter(value = AccessLevel.NONE)
	private final Class<L> listagem;

	private Dep(final String funcionalidadeCampo, final Class<E> entidade, final Class<D> daoClass,
			final Class<F> filtro, final Class<L> listagem, final Dep<?, ?, ?, ?>... dependencias) {
		this.funcionalidadeCampo = funcionalidadeCampo;
		this.entidade = entidade;
		this.daoClass = daoClass;
		this.filtro = filtro;
		this.listagem = listagem;
		this.dependencias.addAll(Arrays.asList(dependencias));
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Dep)) {
			return false;
		}
		final Dep<?, ?, ?, ?> other = (Dep<?, ?, ?, ?>) obj;
		return this.funcionalidadeCampo.equalsIgnoreCase(other.funcionalidadeCampo);
	}

	public Optional<Dep<?, ?, ?, ?>> getDependencia(final String funcionalidadeCampo) {
		return this.dependencias.stream()
				.filter(d -> funcionalidadeCampo.equalsIgnoreCase(this.getFuncionalidadeCampo())).findFirst();
	}

	public Optional<Set<Dep<?, ?, ?, ?>>> getDependencias() {
		return Optional.ofNullable(this.dependencias);
	}

	public Optional<Class<F>> getFiltro() {
		return Optional.ofNullable(this.filtro);
	}

	public Optional<Class<L>> getListagem() {
		return Optional.ofNullable(this.listagem);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.funcionalidadeCampo);
	}

}

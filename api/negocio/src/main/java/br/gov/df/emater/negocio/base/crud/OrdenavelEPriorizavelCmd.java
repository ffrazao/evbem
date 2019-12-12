package br.gov.df.emater.negocio.base.crud;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Ordenavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Priorizavel;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrdenavelEPriorizavelCmd extends Comando {

	@Override
	protected <k, v> void procedimento(final Contexto contexto) throws Exception {
		Object requisicao = contexto.getRequisicao();

		executa(requisicao);
	}

	private void executa(Object requisicao) {
		if (requisicao == null) {
			return;
		}
		if (requisicao instanceof Collection) {
			if (((Collection) requisicao).size() > 0) {
				Optional reg = ((Collection) requisicao).stream().findFirst();
				reg.ifPresent(r -> {
					if (r instanceof Ordenavel) {
						ordenarEPriorizar((Collection<Ordenavel>) requisicao, 1);
					} else {
						executa(r);
					}
				});
			} else {
				return;
			}
		}
		if (requisicao instanceof EntidadeBase) {
			BeanWrapper wrapper = new BeanWrapperImpl(requisicao);
			for (PropertyDescriptor p : wrapper.getPropertyDescriptors()) {
				if (!"class".equalsIgnoreCase(p.getName())) {				
					Object valor = wrapper.getPropertyValue(p.getName());
					executa(valor);
				}
			}
		}
	}

	private void ordenarEPriorizar(final Collection<Ordenavel> ordenaveis, Integer inicio) {
		if (ordenaveis == null || ordenaveis.size() == 0) {
			return;
		}
		
		final AtomicInteger inicioAtomic = new AtomicInteger(inicio);
		final AtomicBoolean priorizavelAtomic = new AtomicBoolean(false);
		ordenaveis.stream()
				.sorted((o1, o2) -> o1 != null && o2 != null && o1.getOrdem() != null && o2.getOrdem() != null
						? o1.getOrdem().compareTo(o2.getOrdem())
						: 0)
				.forEach(o -> {
					if (!priorizavelAtomic.get() && o instanceof Priorizavel) {
						priorizavelAtomic.set(true);
					}
					o.setOrdem(inicioAtomic.getAndIncrement());
					executa(o);
				});
		if (priorizavelAtomic.get()) {
			Collection<Priorizavel> priorizaveis = ordenaveis.stream().filter(p -> Confirmacao.S.equals(((Priorizavel) p).getPrincipal()))
					.map(p -> (Priorizavel) p) .collect(Collectors.toList());
			// atribuir N aos itens não informados
			ordenaveis.stream().filter(p -> ((Priorizavel) p).getPrincipal() == null).forEach(o -> ((Priorizavel) o).setPrincipal(Confirmacao.N));
			if (priorizaveis.size() != 1) {
				if (priorizaveis.size() > 1) {
					boolean primeiro = true;
					for (Priorizavel priori: priorizaveis) {
						priori.setPrincipal(primeiro ? Confirmacao.S: Confirmacao.N);
						primeiro = false;
					}
				} else {
					Priorizavel principal = ((Priorizavel) ordenaveis.stream().filter(o -> 1 == o.getOrdem()).findFirst().get()); 
					principal.setPrincipal(Confirmacao.S);
				}
			}
		}
		
	}

}

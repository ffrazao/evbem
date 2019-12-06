package br.gov.df.emater.negocio.base.crud;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.BibliotecaSpring;
import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.base.Dep;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaFisica;

@Component
public class CriarCmd extends Comando {
	
	@Autowired
	private BibliotecaSpring biblioteca;

	@SuppressWarnings("unchecked")
	@Override
	protected void procedimento(Contexto contexto) throws Exception {

		final Optional<Dep<?, ?, ?, ?>> dep = (Optional<Dep<?, ?, ?, ?>>) contexto.get(AntesCmd.DEPENDENCIA);

		JpaRepository<?, Integer> dao = biblioteca.instanciarBean(dep.get().getDao());//.saveAndFlush( (S) new PessoaFisica());
		dao.save(new PessoaFisica());
		
		contexto.setResposta(
				((Collection<?>) contexto.getRequisicao()).stream()
				.map((reg) -> 1//biblioteca.instanciarBean(dep.get().getDao()).saveAndFlush(reg)
			).collect(Collectors.toList()));
	}

}

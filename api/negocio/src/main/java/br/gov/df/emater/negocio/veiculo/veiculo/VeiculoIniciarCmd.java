package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.principal.RecursoTipo;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.repositorio_principal.entidade.principal.Recurso;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;

@Component
public class VeiculoIniciarCmd extends Comando {

	@Override
	protected void procedimento(Contexto contexto) throws Exception {
		
		Veiculo modelo = (Veiculo) contexto.getRequisicao();
		if (modelo == null) {
			modelo = new Veiculo();
		}
		if (modelo.getProduto() == null) {
			modelo.setProduto(new Produto());
		}
		if (modelo.getProduto().getRecurso() == null) {
			modelo.getProduto().setRecurso(new Recurso());
		}
		modelo.getProduto().getRecurso().setAtivo(Confirmacao.S);		
		modelo.getProduto().getRecurso().setRecursoTipo(RecursoTipo.PRODUTO);
		
		contexto.setResposta(modelo);
	}

}

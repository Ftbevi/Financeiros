package com.ifce.Financeiros.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.ifce.Financeiros.model.Lancamento;
import com.ifce.Financeiros.model.Pessoa;
import com.ifce.Financeiros.model.TipoLancamento;
import com.ifce.Financeiros.repository.Lancamentos;
import com.ifce.Financeiros.repository.Pessoas;
import com.ifce.Financeiros.service.CadastroLancamentos;
import com.ifce.Financeiros.service.NegocioException;
import com.ifce.Financeiros.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
		
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Lancamentos lancamentos;
	
	private Lancamento lancamento;
	private List<Pessoa> todasPessoas;
	
	public void prepararCadastro(){
		this.todasPessoas = this.pessoas.todas();
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}
	
	public List<String> pesquisarDescricoes(String descricao){
		return this.lancamentos.descricoesQueContem(descricao);
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event){
		if(this.lancamento.getDataPagamento() == null){
			this.lancamento.setDataPagamento(
				this.lancamento.getDataVencimento());
		}
	}
	
	@Transactional
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			this.cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage(
					"Lançamento salvo com sucesso!"));
		}catch (NegocioException e){
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos(){
		return TipoLancamento.values();
	}
}

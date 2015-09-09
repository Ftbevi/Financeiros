package com.ifce.Financeiros.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.ifce.Financeiros.model.Lancamento;
import com.ifce.Financeiros.repository.Lancamentos;
import com.ifce.Financeiros.service.CadastroLancamentos;
import com.ifce.Financeiros.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class ConsultaLancamentoBean implements Serializable{

		private static final long serialVersionUID =1L;
		
		@Inject
		private Lancamentos lancamentosRepository;
		
		@Inject
		private CadastroLancamentos cadastro;
		
		private List<Lancamento> lancamentos;
		
		private Lancamento lancamentoSelecionado;
		
		public void excluir(){
			FacesContext context = FacesContext.getCurrentInstance();
			try{
				this.cadastro.excluir(this.lancamentoSelecionado);
				this.consultar();
				context.addMessage(null, new FacesMessage(
						"Lancamento excluído com sucesso!"));
			}catch(NegocioException e){
				FacesMessage mensagem = new FacesMessage(e.getMessage());
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, mensagem);
			}
		}
		
		public void consultar(){
			this.lancamentos = lancamentosRepository.todos();
		}
		
		public List<Lancamento> getLancamentos() {
			return lancamentos;
		}

		public void setLancamentos(List<Lancamento> lancamentos) {
			this.lancamentos = lancamentos;
		}

		public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
			this.lancamentoSelecionado = lancamentoSelecionado;
		}
		
		public Lancamento getLancamentoSelecionado() {
			return lancamentoSelecionado;
		}
	
}

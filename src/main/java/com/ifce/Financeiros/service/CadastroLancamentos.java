package com.ifce.Financeiros.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.ifce.Financeiros.model.Lancamento;
import com.ifce.Financeiros.repository.Lancamentos;
import com.ifce.Financeiros.util.Transactional;

public class CadastroLancamentos implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	@Inject
	private Lancamentos lancamentos;
		
	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException{
		if(lancamento.getDataPagamento() != null && 
				lancamento.getDataPagamento().after(new Date())){
			throw new NegocioException(
					"Data de pagamento não pode ser uma data futura.");
		}
		this.lancamentos.guardar(lancamento);
	}
	
	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException{
		lancamento = this.lancamentos.porId(lancamento.getId());
		if (lancamento.getDataPagamento() != null){
			throw new NegocioException(
					"Não é possível excluir uma lançamento pago!");
		}
		this.lancamentos.remover(lancamento);
	}
}
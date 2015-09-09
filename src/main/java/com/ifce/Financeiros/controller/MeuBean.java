package com.ifce.Financeiros.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MeuBean {

	private String nome;
	private int quantidadeCaracteres;
	
	public void transformar(){
		this.nome = this.nome.toUpperCase();
		this.quantidadeCaracteres = this.nome.length();
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidadeCaracteres() {
		return quantidadeCaracteres;
	}

	public void setQuantidadeCaracteres(int quantidadeCaracteres) {
		this.quantidadeCaracteres = quantidadeCaracteres;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

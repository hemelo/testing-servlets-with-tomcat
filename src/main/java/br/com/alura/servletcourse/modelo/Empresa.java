package br.com.alura.servletcourse.modelo;

import java.util.Date;

public class Empresa {
	private static int implement_id = 0; 
	
	private Integer id;
	private String nome;
	private Date dataAbertura = new Date();
	
	public Empresa() {
		this.id = implement_id;
		implement_id++;
	}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public Date getDataAbertura() {
		return dataAbertura;
	}
}

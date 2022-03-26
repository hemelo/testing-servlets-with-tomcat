package br.com.alura.servletcourse.modelo;

import java.util.Objects;

public class Usuario {
	private String login;
	private String senha;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@Override
	public int hashCode() {
		return Objects.hash(login, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}
	public boolean authenticate(String login, String pwd) {
		return Objects.equals(login, this.login) && Objects.equals(pwd, this.senha);
	}
	
	
}

package br.com.alura.servletcourse.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.servletcourse.modelo.Banco;
import br.com.alura.servletcourse.modelo.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessao = request.getSession();
		if(sessao.getAttribute("usuario") != null) {
			response.sendRedirect("empresas");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/formLogin.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pwd = request.getParameter("senha");
		
		Banco banco = new Banco();
		Usuario usuario = banco.existeUsuario(login, pwd);
		
		if(usuario != null) {
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect("empresas");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/formLogin.jsp");
		rd.forward(request, response);
	}

}

package br.com.alura.servletcourse.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.servletcourse.modelo.Banco;
import br.com.alura.servletcourse.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Banco banco = new Banco();
	
		String paramId = request.getParameter("id");
		
		if(!(paramId == null)) {
			Integer id = Integer.valueOf(paramId);
			
			Empresa empresa = banco.buscaEmpresaPelaId(id);
			
			request.setAttribute("empresa", empresa);
			RequestDispatcher rd = request.getRequestDispatcher("/formAlteraEmpresa.jsp");
			rd.forward(request, response);
		}
		
		List<Empresa> lista = banco.getEmpresas();
		request.setAttribute("empresas", lista);
		
		String valor = request.getHeader("Accept");
		if(valor.contains("text/html")) {
			RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas.jsp");
			rd.forward(request, response);
			
		}
		else if(valor.contains("xml")) {
		    XStream xstream = new XStream();
		    xstream.alias("empresa", Empresa.class);
			String xml = xstream.toXML(lista);
			  
			response.setContentType("application/xml");
			response.getWriter().print(xml); 
			 
		}
		else if (valor.contains("json")){
			Gson gson= new Gson();
			String json = gson.toJson(lista);
			 
			response.setContentType("application/json");
			response.getWriter().print(json);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nomeEmpresa = request.getParameter("nome");
		String paramDataEmpresa = request.getParameter("data");
		
		Date dataAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		Banco banco = new Banco();
		banco.adiciona(empresa);
		
		request.setAttribute("empresa", empresa.getNome());
		response.sendRedirect("empresas");
	}
}

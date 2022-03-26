package br.com.alura.servletcourse.middlewares;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class Inspect implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long antes = System.currentTimeMillis();
		chain.doFilter(request, response);
		long depois = System.currentTimeMillis();
		System.out.println("Tempo de execução:" + (depois - antes));
	}

}

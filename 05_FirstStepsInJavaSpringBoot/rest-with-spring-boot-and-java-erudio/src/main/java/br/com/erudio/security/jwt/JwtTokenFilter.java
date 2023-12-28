package br.com.erudio.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends GenericFilterBean{

	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			try {
				String token = tokenProvider.resolveToken((HttpServletRequest) request);
				if(token != null && tokenProvider.validateToken(token)) {
					Authentication auth = tokenProvider.getAuthentication(token);
					if(auth != null) {
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
				
				chain.doFilter(request, response);
			} catch (Exception e) {
				//PESQUISAR POR MELHOR SOLUCAO
			    HttpServletResponse res = (HttpServletResponse) response;
				var handlerException = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token JWT Inválido!");;
				res.setStatus(handlerException.getStatusCode().value());
		        res.setContentType("application/json");	        
		        ObjectMapper mapper = new ObjectMapper();
		        PrintWriter out = res.getWriter(); 
		        out.print(mapper.writeValueAsString(handlerException));
		        out.flush();
		        return; 

			}
			
		
	}

}

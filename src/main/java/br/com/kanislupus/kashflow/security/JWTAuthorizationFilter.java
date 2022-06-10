package br.com.kanislupus.kashflow.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



//Esse filtro irá analisar se o token do usuário é válido
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUti,
			UserDetailsService userDetailsService) {

		super(authenticationManager);
		this.jwtUtil = jwtUti;
		this.userDetailsService = userDetailsService;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain)
			throws IOException, ServletException {
		
		//Faz as verificações necessárias referente ao token
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthetication(header.substring(7));
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		//Se ele passar pelo processo acima, ele irá continuar a requisição
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthetication(String token) {
		
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user  = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, null);
		}
		return null;
	}
}


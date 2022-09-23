package com.cleber.helpDeskapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cleber.helpDeskapi.dtos.CrendenciaisDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe de filtro de autenticação: automaticamente o spring entende que ele
 * vai interceptar o login
 */
public class JwTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// principal interface de autencticação instancia que retorna um sinalizador de
	// autenticação do usuario valido ou null
	private AuthenticationManager authenticationManager;
	private JwTUtil jwTUtil;

	public JwTAuthenticationFilter(AuthenticationManager authenticationManager, JwTUtil jwTUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwTUtil = jwTUtil;
	}

	// metado que faz tentativa de autenticação.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CrendenciaisDto crends = new ObjectMapper().readValue(request.getInputStream(), CrendenciaisDto.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					crends.getEmail(), crends.getSenha(), new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Casi autenciação dê sucesso cai nesse caso
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwTUtil.generationToken(userName);
		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Authorization", "Bearer " + token);
	}

	// Caso não tenha sucesso na autenticação
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	private CharSequence json() {
		Long date = new Date().getTime();
		return "{" + "\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\","
				+ "\"message\": \"Email ou Senha inválidos\", " + "\"path\": \"/login\"}";

	}

}

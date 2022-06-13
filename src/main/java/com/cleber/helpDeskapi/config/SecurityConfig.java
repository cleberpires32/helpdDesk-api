package com.cleber.helpDeskapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cleber.helpDeskapi.security.JwTAuthenticationFilter;
import com.cleber.helpDeskapi.security.JwTUtil;
import com.cleber.helpDeskapi.security.JwtAuthorizationFilter;

/** Classe de configuração do Spring Security para autorização e authentic**/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//Representa o ambiente no qual o meu aplicativo está sendo executado. 
	//Poder usado para obter os perfis de test do Bd para habilitar.  	
	@Autowired
	private Environment env; 
	@Autowired
	private JwTUtil jwTUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final String[] PUBLIC_MATCHER = {"/h2-console/**"};
	
	//chamada do Cors, faz com que a configuração consegue enchergar o @Bean corsconfigurationSource  
	//If a bean by the name of corsFilter is provided
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		//Esta extensão .and().csrf().disable() desabilita a proteção contra ataque csrf que protege armazenamento e sessão de usuario.
		http.cors().and().csrf().disable(); 
		
		//registrar filtro de autenticação sem security
		http.addFilter(new JwTAuthenticationFilter(authenticationManager(), jwTUtil));
		
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwTUtil, userDetailsService));
		
		//aqui estou dizendo que qualquer requisição vier deste public_matcher seja permitido logo outra requisição diferente seja autenticada.
		http.authorizeRequests().antMatchers(PUBLIC_MATCHER).permitAll().anyRequest().authenticated();
		//Garantia de que esta sessão de usuario não esta sendo criada no cliente
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	//Class que configura a liberação de requisões permitidas nas chamadas de POST,GET,PUT,DELET ...
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		// Por padrão ja libera o GET e POST
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues(); 
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}

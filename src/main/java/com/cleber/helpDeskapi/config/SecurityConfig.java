package com.cleber.helpDeskapi.config;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/** Classe de configuração do Spring Security para autorização e authentic**/
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//Representa o ambiente no qual o meu aplicativo está sendo executado. Poder usado para obter os perfis de test do Bd para habilitar.  	
	@Autowired
	private Environment env; 
	
	private static final String[] PUBLIC_MATCHER = {"/h2-console/**"};
	
	/**chamada do Cors, faz com que a configuração consegue enchergar o @Bean corsconfigurationSource  
	 * If a bean by the name of corsFilter is
	 * provided,**/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		//Esta extensão .and().csrf().disable() desabilita a proteção contra ataque csrf que protege armazenamento e sessão de usuario.
		http.cors().and().csrf().disable(); 
		//aqui estou dizendo que qualquer requisição vier deste public_matcher seja permitido logo outra requisição diferente seja autenticada.
		http.authorizeRequests().antMatchers(PUBLIC_MATCHER).permitAll().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Garantia de que esta sessão de usuario não esta sendo criada no cliente
	}
	
	//Class que configura a liberação de requisões permitidas nas chamadas de POST,GET,PUT,DELET ...
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues(); // Por padrão ja libera o GET e POST
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}

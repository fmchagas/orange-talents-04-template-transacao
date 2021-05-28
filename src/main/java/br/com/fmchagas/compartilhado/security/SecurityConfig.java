package br.com.fmchagas.compartilhado.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.authorizeRequests()
			 	.antMatchers("/actuator/prometheus").permitAll()
				.antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_transacao:read")
				.anyRequest().authenticated()
			.and()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.oauth2ResourceServer(rsc -> rsc.jwt());
	}
}
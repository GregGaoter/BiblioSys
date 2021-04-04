package com.dsi.bibliosys.biblioback.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.reactive.config.CorsRegistry;

import com.dsi.bibliosys.biblioback.service.DetailsUtilisateurService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtPointEntreeAuthentification jwtPointEntreeAuthentification;
	@Autowired
	private DetailsUtilisateurService detailsUtilisateurService;
	@Autowired
	private JwtFiltreRequete jwtFiltreRequete;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsUtilisateurService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE")
				.allowedHeaders("*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated();
//		http.formLogin().defaultSuccessUrl("/").permitAll();
//		http.httpBasic();
//		http.csrf().disable();
//		http.logout().logoutSuccessUrl("/");
		
		http.cors();
		http.csrf().disable().headers().frameOptions().deny();
		http.authorizeRequests().antMatchers("/authentification").permitAll().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(jwtPointEntreeAuthentification);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFiltreRequete, UsernamePasswordAuthenticationFilter.class);
	}

}

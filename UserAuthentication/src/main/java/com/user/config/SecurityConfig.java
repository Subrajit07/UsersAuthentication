package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(csrf->csrf.disable())
								.sessionManagement(session->
																session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
								.authorizeHttpRequests(auth->
																auth.requestMatchers("/auth/**",
											                            "/error",       
											                            "/v3/api-docs/**",
											                            "/swagger-ui/**",
											                            "/swagger-ui.html").permitAll()
																.anyRequest().authenticated()
																);
		return httpSecurity.build();
	}
}

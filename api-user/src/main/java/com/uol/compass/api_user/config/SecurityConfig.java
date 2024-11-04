package com.uol.compass.api_user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.uol.compass.api_user.jwt.JwtAuthenticationEntryPoint;
import com.uol.compass.api_user.jwt.JwtAuthorizationFilter;

@EnableWebMvc
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	private static final String[] DOCUMENTATION_OPENAPI = {
	        "/docs/index.html",
	        "/docs-park.html", "/docs-park/**",
	        "/v3/api-docs/**",
	        "/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
	        "/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**"
	};
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(x -> x.disable())
				.formLogin(x -> x.disable())
				.httpBasic(x -> x.disable())
				.authorizeHttpRequests(x -> x
						.requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
						.requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
						.anyRequest().authenticated())
				.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(x -> x.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
				.build();
	}
    
    @Bean
    JwtAuthorizationFilter jwtAuthorizationFilter() {
    	return new JwtAuthorizationFilter();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
}


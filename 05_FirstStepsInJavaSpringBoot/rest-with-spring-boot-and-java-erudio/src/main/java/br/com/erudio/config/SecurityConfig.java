package br.com.erudio.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.erudio.security.jwt.JwtConfigurer;
import br.com.erudio.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		Pbkdf2PasswordEncoder pbkdf2 = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
		encoders.put("pbkdf2", pbkdf2);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2);
		return passwordEncoder;
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .build();
	}
	

	@Bean
	protected SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic(basic -> basic.disable())
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authorizeHttpRequests(
							authorizeHttpRequestsConfigurer -> authorizeHttpRequestsConfigurer
								.requestMatchers(
										"/auth/signin",
										"/auth/refresh/**",
										"/api-docs/**",
										"/swagger-ui/**"
						).permitAll()
						 .requestMatchers("/api/**").authenticated()
						 .requestMatchers("/users").denyAll()
					)
					.cors(cors -> {} )			
					.apply(new JwtConfigurer(tokenProvider));
		return http.build();
	}
}

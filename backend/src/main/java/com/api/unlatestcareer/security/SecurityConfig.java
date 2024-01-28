package com.api.unlatestcareer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
	JwtAuthorizationFilter jwtAuthorizationFilter() {
	    return new JwtAuthorizationFilter();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(withDefaults())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/profiles/live/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "**").permitAll()
                        .anyRequest().authenticated()).sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(AbstractHttpConfigurer::disable);
        httpSecurity.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/usuario/login")
                .requestMatchers(HttpMethod.GET,"/profiles/live/**")
                .requestMatchers(HttpMethod.OPTIONS, "**");
    }
}

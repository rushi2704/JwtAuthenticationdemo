package com.jwtauthdemo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtauthdemo.jwt.JwtAuthenticationEntryPoint;
import com.jwtauthdemo.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
        .csrf()
        .disable()
        .cors()
        .disable()
        .authorizeHttpRequests() .requestMatchers("/authenticate").permitAll()
        .requestMatchers(HttpMethod.OPTIONS).permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(point).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

   http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

     return http.build();
	
	
}
}
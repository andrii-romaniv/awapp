package com.romvol.awapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;


@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        
        return http
                .csrf().disable()
                .httpBasic().securityContextRepository(new WebSessionServerSecurityContextRepository())
                .and()
                .authorizeExchange()
                //TODO Just temporal
                .pathMatchers(HttpMethod.GET, "/customers/**").permitAll()
                .anyExchange().permitAll()
                .and()
                .build();
    }
}

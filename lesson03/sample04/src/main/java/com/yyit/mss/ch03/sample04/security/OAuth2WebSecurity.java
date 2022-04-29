package com.yyit.mss.ch03.sample04.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author yyit
 **/
@EnableWebFluxSecurity
public class OAuth2WebSecurity {



    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,PermissionAuthorizationManager permissionAuthorizationManager) {
        // @formatter:off
        http
                .authorizeExchange()
                .pathMatchers("/api/oauth2/**").permitAll()
                .anyExchange().access(permissionAuthorizationManager)
                .and()
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        http.csrf().disable();
        return http.build();
        // @formatter:on
    }

}

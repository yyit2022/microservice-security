package com.yyit.mss.lesson4.sample02.apigateway.config;

import com.yyit.mss.lesson4.sample02.apigateway.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private PermissionAuthorizationManager permissionAuthorizationManager;
    @Autowired
    private CustomServerAccessDeniedHandler customServerAccessDeniedHandler;
    @Autowired
    private CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        http.oauth2ResourceServer()
                .jwt()
;
        http.authorizeExchange()
                .pathMatchers(ignoreUrlsConfig.getUrls()).permitAll()
                .anyExchange().access(permissionAuthorizationManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customServerAccessDeniedHandler)
                .authenticationEntryPoint(customServerAuthenticationEntryPoint)
                .and()
                .csrf().disable()

        ;
        return http.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new RealmRoleConverter());
        return jwtConverter;
    }

}
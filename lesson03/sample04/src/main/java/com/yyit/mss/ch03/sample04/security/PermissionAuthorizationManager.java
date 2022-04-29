package com.yyit.mss.ch03.sample04.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 权限检测
 * </p>
 *
 * @author yyit
 **/
@Component
public class PermissionAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        //请求资源
        String requestPath = exchange.getRequest().getURI().getPath();
        return authenticationMono.map(auth ->
                        new AuthorizationDecision(checkAuthorities(exchange, auth, requestPath)))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    //权限校验
    private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth, String requestPath) {
        Jwt principal = (Jwt) auth.getPrincipal();
        logger.info("访问的URL是：{},oauth2 客户端ID:{}",requestPath, principal.getClaims().get("sub"));
        return true;
    }
}

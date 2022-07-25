package com.yyit.mss.lesson4.sample02.apigateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PermissionAuthorizationManager  implements ReactiveAuthorizationManager<AuthorizationContext>  {

    /**
     * 实现权限验证判断
     * @param authentication
     * @param object
     * @return
     */
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext object) {
        return mono.filter(au -> {
                    Object obj = au.getPrincipal();
                    log.info("鉴权逻辑，根据结果返回true或者false");
                    return true;//鉴权逻辑
                }).map(a -> new AuthorizationDecision(true))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}

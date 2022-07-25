package com.yyit.mss.sample03.oauth2.server.jwt;

import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

/**
 * 个性化 JWT token
 */
@Component
public class CustomOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        // 添加一个自定义头
        context.getHeaders().header("client-id", context.getRegisteredClient().getClientId());

        context.getClaims().claim("HELLO","yanpingping");
    }
}
package com.yyit.mss.lesson4.sample02.apigateway.filters;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.yyit.mss.lesson4.sample02.apigateway.constants.AuthConstants;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);

        if (StringUtils.isBlank(token)) {
            return chain.filter(exchange);
        }else {
            log.info("访问令牌: {}",token);
        }

        String realToken = token.replace(AuthConstants.JWT_TOKEN_PREFIX, "");

        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(realToken);
            String payload = jwsObject.getPayload().toString();
            JSONObject jsonObject = JSON.parseObject(payload);
            String jti = jsonObject.getString("jti");

            ServerHttpRequest request = exchange.getRequest().mutate().header("user", payload).build();
            exchange = exchange.mutate().request(request).build();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

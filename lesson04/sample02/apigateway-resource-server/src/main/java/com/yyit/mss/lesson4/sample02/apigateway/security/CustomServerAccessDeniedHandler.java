package com.yyit.mss.lesson4.sample02.apigateway.security;



import com.alibaba.fastjson.JSON;
import com.yyit.mss.lesson4.sample02.apigateway.common.GlobalServiceMsgCode;
import com.yyit.mss.lesson4.sample02.apigateway.util.RespUtils;
import com.yyit.mss.lesson4.sample02.apigateway.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Slf4j
@Component
public class CustomServerAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        log.error("<< Gateway::RestAuthenticationEntryPoint::AUTH >> error: 没有授权, info: [{}]", denied.getMessage());
        return RespUtils.createAccessDeniedResponse(exchange.getResponse(), GlobalServiceMsgCode.USER_NO_PERMISSION);
    }
}

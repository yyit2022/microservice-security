package com.yyit.mss.lesson4.sample02.apigateway.util;


import com.alibaba.fastjson.JSON;
import com.yyit.mss.lesson4.sample02.apigateway.common.GlobalServiceMsgCode;
import com.yyit.mss.lesson4.sample02.apigateway.common.SystemMsgJsonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class RespUtils {
    public static Mono<Void> createAccessDeniedResponse(ServerHttpResponse resp, GlobalServiceMsgCode code) {
        setRespStatus(resp);
        return resp.writeWith(Mono.just(resp.bufferFactory()
                .wrap(setRespMsgJsonStr(code).getBytes(StandardCharsets.UTF_8))));
    }

    private static String setRespMsgJsonStr(GlobalServiceMsgCode code) {
        return JSON.toJSONString(findRespMsg(code));
    }

    private static SystemMsgJsonResponse findRespMsg(GlobalServiceMsgCode code) {
        if (code == GlobalServiceMsgCode.SUCCESS) {
            return SystemMsgJsonResponse.success(code);
        }
        return SystemMsgJsonResponse.fail(code);
    }


    private static void setRespStatus(ServerHttpResponse resp) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
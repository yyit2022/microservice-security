package com.yyit.mss.lesson4.sample02.msone.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/one")
public class HomeController {

    @GetMapping()
    public String home(@RequestHeader(name="user",required = false) String userInfo){
        System.out.println(userInfo);
        return "One application";
    }


    @GetMapping("/open")
    public String openApi(){
        return "开放式 API";
    }

}

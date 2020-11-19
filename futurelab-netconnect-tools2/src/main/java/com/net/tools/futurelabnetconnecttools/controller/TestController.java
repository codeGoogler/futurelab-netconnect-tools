package com.net.tools.futurelabnetconnecttools.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api(value = "TestController" , tags = "机构管理前端控制器")
@RestController
@RequestMapping("/sys/TestController")
public class TestController {

    @Autowired
    RestTemplate restTemplate;



    @GetMapping("/postCheckPrint")
    @ApiOperation(value = "岗位数据对比，打印", httpMethod = "GET")
    public String postCheckPrint() {
        String response = restTemplate.getForObject( String.format("https://www.baidu.com"), String.class);
        System.out.println(response);
        return  response;
    }

}

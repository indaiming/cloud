package com.provider5552.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@Api("5552测试接口")
public class Demo5552 {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hi/{name}")
    public String doHello(@PathVariable String name){
        System.out.println("Demo5551~~hello"+name);
        return "Demo5551~~hello"+name;
    }

    @ApiOperation(value = "获取端口接口测试")
    @GetMapping("/getPort/{info}")
    public String getPort(@PathVariable("info") String info){
        System.out.println("Demo5552====>port:"+port+",======>info:"+info);
        return "Demo5552====>port:"+port+",======>info:"+info;
    }

    @ApiOperation(value = "输出信息接口")
    @GetMapping("/outInfo")
    public String outInfo(@RequestParam(value = "info") String info){
        return "Demo5552=====输出信息=====>info:"+info;
    }
}

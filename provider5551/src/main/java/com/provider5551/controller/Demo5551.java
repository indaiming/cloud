package com.provider5551.controller;

import com.provider5551.fegin.Fegin5551;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Api("5551测试接口")
public class Demo5551 {

    @Autowired
    private Fegin5551 fegin5551;

    @GetMapping("/hi/{name}")
    public String doHello(@PathVariable String name){
        System.out.println("Demo5551~~hello"+name);
        return "Demo5551~~hello"+name;
    }

    @GetMapping("/getPort/{info}")
    @ApiOperation(value = "调用fegin接口测试@PathVariable")
    public String getPort(@PathVariable("info") String info){
        return fegin5551.getPort(info) ;
    }

    @GetMapping("/outInfo")
    @ApiOperation(value = "调用fegin接口测试@RequestParam")
    public String outInfo(@RequestParam("info") String info){
        return fegin5551.outInfo(info) ;
    }
}

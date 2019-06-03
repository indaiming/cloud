package com.provider5552.controller;

import com.provider5552.entity.User;
import com.provider5552.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController()
@Api("5552测试接口")
public class Demo5552 {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hi/{name}")
    public String doHello(@PathVariable String name) {
        System.out.println(port + "~~hello" + name);
        return port + "~~hello" + name;
    }

    @GetMapping("/myerror")
    public String myerror() throws MyException{
        throw new MyException("MyException自定义错误");
    }

    @GetMapping("/hahaha")
    public String error() throws Exception{
        System.out.println("fdsdf");
        throw new Exception("Exception错误");
    }

    @ApiOperation(value = "获取端口接口测试")
    @GetMapping("/getPort/{info}")
    public String getPort(@PathVariable("info") String info) {
        System.out.println("Demo5552====>port:" + port + ",======>info:" + info);
        return port + "======>info:" + info;
    }

    @ApiOperation(value = "输出信息接口")
    @GetMapping("/outInfo")
    public String outInfo(@RequestParam(value = "info") String info) {
//        int i = 2/0;//模拟异常
        return port + "==输出信息=====>info:" + info;
    }

    @GetMapping("/getUserNames")
    @ApiOperation(value = "输出用户名接口测试")
    public String getUserNames() {

        return "1.天魁星…呼保义…宋江.\n" +
                "2.天罡星…玉麒麟…卢俊义.\n" +
                "3.天机星…智多星…吴用.\n" +
                "4.天闲星…入云龙…公孙胜";
    }

//    @PostMapping("/getUser")
//    @ApiOperation(value = "用户接口测试")
//    public User getUser(){
//        User user = new User();
//        user.setName("5551");
//        user.setPort(port);
//        user.setNowTime(new Date());
//        return user;
//    }

    @PostMapping("/getUser")
    @ApiOperation(value = "用户接口测试")
    public User getUser(@RequestBody User user) {
        User user1 = new User();
        user.setName(port + user.getName());
        user.setPort(port);
        user.setNowTime(new Date());
        return user;
    }
}

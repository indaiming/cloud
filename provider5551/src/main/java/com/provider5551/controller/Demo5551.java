package com.provider5551.controller;

import com.provider5551.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController()
@Api("5551测试接口")
public class Demo5551 {

//    @Autowired
//    private Fegin5551 fegin5551;

    @Value("${eurekaServer.service-5552}")
    String service_id_5552;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hi/{name}")
    public String doHello(@PathVariable String name) {
        System.out.println(port + "~~hello" + name);
        return port + "~~hello" + name;
    }

    @GetMapping("/getPort/{info}")
    @ApiOperation(value = "调用fegin接口测试@PathVariable")
    public String getPort(@PathVariable("info") String info) {
//        return fegin5551.getPort(info);
        return info+"==>port===>"+port;
    }

    @GetMapping("/outInfo")
    @ApiOperation(value = "调用fegin接口测试@RequestParam")
    public String outInfo(@RequestParam("info") String info) {
//        return fegin5551.outInfo(info);
        return info+"==>port===>"+port;
    }

    @GetMapping("/getByRestTemplet")
    @ApiOperation(value = "调用RestTemplet接口测试")
    public String getByRestTemplet() {
        return restTemplate.getForObject("http://${eurekaServer.service-5552}/getUserNames", String.class);
    }

    @GetMapping("/getSH")
    @ApiOperation(value = "输出用户名接口测试")
    public String getSH() {
        return "1.天魁星…呼保义…宋江";
    }

    @PostMapping("/getUserByRestTemp")
    @ApiOperation(value = "RestTemp输出用户接口测试")
    public User getUserByRestTemp() {
        User user = new User();
        user.setName("daim");
        user.setPort(port);
        user.setNowTime(new Date());
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("http://"+service_id_5552+"/getUser", user, User.class);
        return userResponseEntity.getBody();
    }
//    @PostMapping("/getUserByFegin")
//    @ApiOperation(value = "Fegin输出用户接口测试")
//    public User getUserByFegin() {
//        User user = new User();
//        user.setName(port+"");
//        user.setPort(port);
//        user.setNowTime(new Date());
//        return fegin5551.getUserByFegin(user);
//    }

}

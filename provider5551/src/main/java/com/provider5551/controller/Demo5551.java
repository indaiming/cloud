package com.provider5551.controller;

        import com.provider5551.entity.User;
        import com.provider5551.fegin.Fegin5551;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.client.RestTemplate;

@RestController()
@Api("5551测试接口")
public class Demo5551 {

    @Autowired
    private Fegin5551 fegin5551;

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
        return fegin5551.getPort(info);
    }

    @GetMapping("/outInfo")
    @ApiOperation(value = "调用fegin接口测试@RequestParam")
    public String outInfo(@RequestParam("info") String info) {
        return fegin5551.outInfo(info);
    }

    @GetMapping("/getByRestTemplet")
    @ApiOperation(value = "调用RestTemplet接口测试")
    public String getByRestTemplet() {
        return restTemplate.getForObject("http://PROVIDER/getUserNames", String.class);
    }

    @GetMapping("/getSH")
    @ApiOperation(value = "输出用户名接口测试")
    public String getSH() {
        return "1.天魁星…呼保义…宋江";
    }

    @PostMapping("/getUserByRestTemp")
    @ApiOperation(value = "输出用户名接口测试")
    public User getUserNames() {
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("http://PROVIDER/getUser", null, User.class);
        return userResponseEntity.getBody();
    }
}

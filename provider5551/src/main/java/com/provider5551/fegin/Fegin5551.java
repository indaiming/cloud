package com.provider5551.fegin;

import com.provider5551.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(serviceId = "${eurekaServer.service-5552}")
public interface Fegin5551 {

    @GetMapping("/getPort/{info}")
    String getPort(@PathVariable("info") String info);

    @GetMapping("/outInfo")
    String outInfo(@RequestParam(value="info") String info);

    @PostMapping("/getUser")
    User getUserByFegin(@RequestBody User user);
}

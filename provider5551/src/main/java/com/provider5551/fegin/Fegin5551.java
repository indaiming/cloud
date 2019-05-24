package com.provider5551.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PROVIDER")
public interface Fegin5551 {

    @GetMapping("/getPort/{info}")
    String getPort(@PathVariable("info") String info);

    @GetMapping("/outInfo")
    String outInfo(@RequestParam(value="info") String info);

}

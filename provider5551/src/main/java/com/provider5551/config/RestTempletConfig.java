package com.provider5551.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempletConfig {

    //声明 bean
    @Bean
    @LoadBalanced   //增加 load balance 特性.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package com.provider5552;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableDiscoveryClient
public class Provider5552Application {

	public static void main(String[] args) {
		SpringApplication.run(Provider5552Application.class, args);
	}

//	LocalDate、LocalTime、LocalDateTime是Java 8开始提供的时间日期API，主要用来优化Java 8以前对于时间日期的处理操作。然而，我们在使用Spring Boot或使用Spring Cloud Feign的时候，往往会发现使用请求参数或返回结果中有LocalDate、LocalTime、LocalDateTime的时候会发生各种问题。
//@Bean
//public ObjectMapper serializingObjectMapper() {
//	LocalDate parse = LocalDate.parse("2014-02-21");
//	System.out.println(parse.toString());
//	ObjectMapper objectMapper = new ObjectMapper();
//	objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//	objectMapper.registerModule(new JavaTimeModule());
//	return objectMapper;
//}

}

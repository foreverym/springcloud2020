package com.wy.springcoud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableEurekaClient //支持eureka,需要搭配httpclient一起使用
//@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderMain8011 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8011.class, args);
    }
}

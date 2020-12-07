package com.wy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : WangYB
 * @time: 2020/12/7  20:15
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain8021 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain8021.class);
    }

}

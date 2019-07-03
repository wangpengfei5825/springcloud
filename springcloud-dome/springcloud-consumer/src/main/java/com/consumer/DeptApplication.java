package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication(exclude= DataSourceAutoConfiguration.class,scanBasePackages="com.consumer")
//注册中心（什么都可以不止eureka）
//@EnableDiscoveryClient
////服务的熔断
//@EnableCircuitBreaker
@SpringCloudApplication
//启动feign
@EnableFeignClients
public class DeptApplication {
    @Bean
    @LoadBalanced
    public RestTemplate  config(){
        return  new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(DeptApplication.class);
    }
}

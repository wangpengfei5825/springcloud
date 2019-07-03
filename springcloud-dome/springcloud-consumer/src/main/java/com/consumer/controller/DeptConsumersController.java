package com.consumer.controller;

import com.consumer.clicent.DeptClicent;
import com.consumer.entity.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumers")
@DefaultProperties(defaultFallback = "list3")
public class DeptConsumersController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private DeptClicent  deptClicent;
//    @Autowired
//    private RibbonLoadBalancerClient  ribbonLoadBalancerClient;
    @GetMapping("/ss")
    public Dept[]  list(){
//        List<ServiceInstance> instances = discoveryClient.getInstances("springcloud-api");
//        ServiceInstance serviceInstance = instances.get(0);
//
//        String  url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/test/dept";
        //默认轮询
//        ServiceInstance choose = ribbonLoadBalancerClient.choose("springcloud-api");
//
//        String  url="http://"+choose.getHost()+":"+choose.getPort()+"/test/dept";

        String  url="http://springcloud-api/test/dept";

        return  restTemplate.getForObject(url,Dept[].class);
    }
    @GetMapping("/ss1/{id}")
    //失败容错(默认时间1s如果1s内没返回就走list3方法)
  //  @HystrixCommand(fallbackMethod = "list3")
//    @HystrixCommand(commandProperties={
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    }
//    )
    @HystrixCommand
    public String  list1(@PathVariable("id") Integer id){
        String  url="http://springcloud-api/test/dept1/"+id;

        return  restTemplate.getForObject(url,String.class);
    }

    public String  list3(){
        String  a="服务器蹦了";
        return a;
    }

    @GetMapping("ss2/{id}")
    public   Dept[]  feignTest(@PathVariable("id")Integer id){
       return deptClicent.feignTest(id);
    }
}

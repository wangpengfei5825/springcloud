package com.cloud.controller;

import com.cloud.entity.Dept;
import com.cloud.service.DeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/dept")
    public List<Dept>  list(){
     return    deptService.list();
    }
    @GetMapping("/dept1/{id}")
    public List<Dept>  list1(@PathVariable("id") Integer id){
        if (id%2==0){
            throw  new RuntimeException("奔溃了");
        }
        return    deptService.list();
    }

}

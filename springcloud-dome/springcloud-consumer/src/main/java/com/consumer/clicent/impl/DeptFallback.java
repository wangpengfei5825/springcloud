package com.consumer.clicent.impl;

import com.consumer.clicent.DeptClicent;
import com.consumer.entity.Dept;
import org.springframework.stereotype.Component;

@Component
public class DeptFallback implements DeptClicent {
    @Override
    public Dept[] feignTest(Integer id) {
        Dept dept = new Dept();
        dept.setDeptName("被熔断了");
        Dept[] depts = new Dept[]{dept};
        return depts ;
    }
}

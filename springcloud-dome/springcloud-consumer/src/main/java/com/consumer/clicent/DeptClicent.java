package com.consumer.clicent;

import com.consumer.clicent.impl.DeptFallback;
import com.consumer.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "springcloud-api",fallback = DeptFallback.class)
public interface DeptClicent {
    @GetMapping("test/dept1/{id}")
    Dept[]  feignTest(@PathVariable("id") Integer id);
}

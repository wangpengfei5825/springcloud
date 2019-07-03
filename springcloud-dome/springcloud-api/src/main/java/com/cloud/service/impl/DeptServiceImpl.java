package com.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.dao.DeptDao;
import com.cloud.entity.Dept;
import com.cloud.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;
    @Override
    public List<Dept> list() {
        QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
        return deptDao.selectList(deptQueryWrapper);
    }
}

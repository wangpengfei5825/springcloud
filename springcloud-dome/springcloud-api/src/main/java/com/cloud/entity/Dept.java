package com.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Dept implements Serializable {
    @TableId(value = "dept_id", type = IdType.AUTO)
    private  Integer  deptId;
    private  String deptName;
    private  String dbSource;
}

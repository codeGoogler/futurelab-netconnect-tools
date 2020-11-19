package com.net.tools.futurelabnetconnecttools.service.provider.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.net.tools.futurelabnetconnecttools.common.base.BaseEntity;
import lombok.Data;

/**
* @ClassName Student
* @Description  entity
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
@Data
@TableName("student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "link_id", type = IdType.AUTO)
    private  Integer id ;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    @TableField(exist = false)
    private int stuId;
}

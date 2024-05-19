package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 班级信息：(ClassInformation)表实体类
 *
 */
@TableName("`class_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ClassInformation implements Serializable {

    // ClassInformation编号
    @TableId(value = "class_information_id", type = IdType.AUTO)
    private Integer class_information_id;

    // 班级名称
    @TableField(value = "`class_name`")
    private String class_name;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

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
 * 学院信息：(CollegeInformation)表实体类
 *
 */
@TableName("`college_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class CollegeInformation implements Serializable {

    // CollegeInformation编号
    @TableId(value = "college_information_id", type = IdType.AUTO)
    private Integer college_information_id;

    // 学院名称
    @TableField(value = "`college_name`")
    private String college_name;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

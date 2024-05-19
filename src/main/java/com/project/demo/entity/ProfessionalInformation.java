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
 * 专业信息：(ProfessionalInformation)表实体类
 *
 */
@TableName("`professional_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProfessionalInformation implements Serializable {

    // ProfessionalInformation编号
    @TableId(value = "professional_information_id", type = IdType.AUTO)
    private Integer professional_information_id;

    // 专业名称
    @TableField(value = "`professional_name`")
    private String professional_name;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

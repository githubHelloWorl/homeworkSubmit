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
 * 教师用户：(TeacherUsers)表实体类
 *
 */
@TableName("`teacher_users`")
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherUsers implements Serializable {

    // TeacherUsers编号
    @TableId(value = "teacher_users_id", type = IdType.AUTO)
    private Integer teacher_users_id;

    // 教师姓名
    @TableField(value = "`teachers_name`")
    private String teachers_name;
    // 教师性别
    @TableField(value = "`teacher_gender`")
    private String teacher_gender;
    // 联系电话
    @TableField(value = "`contact_number`")
    private String contact_number;
    // 学院名称
    @TableField(value = "`college_name`")
    private String college_name;

    // 专业名称
    @TableField(value = "`professional_name`")
    private String professional_name;
    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;
    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}

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
 * 作业信息：(AssignmentInformation)表实体类
 *
 */
@TableName("`assignment_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class AssignmentInformation implements Serializable {

    // AssignmentInformation编号
    @TableId(value = "assignment_information_id", type = IdType.AUTO)
    private Integer assignment_information_id;

    // 教师用户
    @TableField(value = "`teacher_users`")
    private Integer teacher_users;
    // 教师姓名
    @TableField(value = "`teachers_name`")
    private String teachers_name;
    // 课程封面
    @TableField(value = "`course_cover`")
    private String course_cover;
    // 作业标题
    @TableField(value = "`assignment_title`")
    private String assignment_title;
    // 作业内容
    @TableField(value = "`assignment_content`")
    private String assignment_content;

    // 状态
    @TableField(value = "`status`")
    private String status;

    // 课程名称
    @TableField(value = "`course_name`")
    private String course_name;


    // 点击数
    @TableField(value = "hits")
    private Integer hits;

    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;








    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

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
 * 作业批改：(HomeworkCorrection)表实体类
 *
 */
@TableName("`homework_correction`")
@Data
@EqualsAndHashCode(callSuper = false)
public class HomeworkCorrection implements Serializable {

    // HomeworkCorrection编号
    @TableId(value = "homework_correction_id", type = IdType.AUTO)
    private Integer homework_correction_id;

    // 教师用户
    @TableField(value = "`teacher_users`")
    private Integer teacher_users;
    // 教师姓名
    @TableField(value = "`teachers_name`")
    private String teachers_name;
    // 学生用户
    @TableField(value = "`student_users`")
    private Integer student_users;
    // 学生姓名
    @TableField(value = "`student_name`")
    private String student_name;
    // 课程名称
    @TableField(value = "`course_name`")
    private String course_name;
    // 作业标题
    @TableField(value = "`assignment_title`")
    private String assignment_title;
    // 作业成绩
    @TableField(value = "`homework_grades`")
    private Integer homework_grades;
    // 批改内容
    @TableField(value = "`correction_content`")
    private String correction_content;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}

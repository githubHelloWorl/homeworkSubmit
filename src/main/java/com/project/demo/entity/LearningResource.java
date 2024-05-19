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
 * 学习资源：(LearningResource)表实体类
 *
 */
@TableName("`learning_resource`")
@Data
@EqualsAndHashCode(callSuper = false)
public class LearningResource implements Serializable {

    // LearningResource编号
    @TableId(value = "learning_resource_id", type = IdType.AUTO)
    private Integer learning_resource_id;

    // 教师用户
    @TableField(value = "`teacher_users`")
    private Integer teacher_users;
    // 教师姓名
    @TableField(value = "`teachers_name`")
    private String teachers_name;
    // 资源名称
    @TableField(value = "`resource_name`")
    private String resource_name;
    // 学院名称
    @TableField(value = "`college_name`")
    private String college_name;
    // 专业名称
    @TableField(value = "`professional_name`")
    private String professional_name;

    // 资源图片
    @TableField(value = "`resource_images`")
    private String resource_images;
    // 资源文档
    @TableField(value = "`resource_document`")
    private String resource_document;
    // 资源视频
    @TableField(value = "`resource_video`")
    private String resource_video;
    // 资源内容
    @TableField(value = "`resource_content`")
    private String resource_content;

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

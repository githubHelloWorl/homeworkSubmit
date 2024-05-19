package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.HomeworkSubmission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作业提交：(HomeworkSubmission)Mapper接口
 *
 */
@Mapper
public interface HomeworkSubmissionMapper extends BaseMapper<HomeworkSubmission>{

    Integer updateSubmissionStatus(Integer id);
}

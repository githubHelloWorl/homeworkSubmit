package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.HomeworkCorrection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 作业批改：(HomeworkCorrection)Mapper接口
 *
 */
@Mapper
public interface HomeworkCorrectionMapper extends BaseMapper<HomeworkCorrection>{

    List<String> getNameList();
}

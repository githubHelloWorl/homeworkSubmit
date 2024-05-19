package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.LearningResource;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.mapping.Index;

import java.util.List;
import java.util.Map;

/**
 * 学习资源：(LearningResource)Mapper接口
 *
 */
@Mapper
public interface LearningResourceMapper extends BaseMapper<LearningResource>{

    List<LearningResource> getList(Integer startIndex, Integer size, Integer userId);

    List<String> getNameList(Integer userId);
}

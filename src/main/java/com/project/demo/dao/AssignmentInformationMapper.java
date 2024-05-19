package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.AssignmentInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 作业信息：(AssignmentInformation)Mapper接口
 *
 */
@Mapper
public interface AssignmentInformationMapper extends BaseMapper<AssignmentInformation>{

    List<Map<String,Object>> getList(Integer startIndex, Integer size);

    List<Map<String,Object>> getStudentAss(Integer userId);

    // 发布
    Integer toPublic(Integer id);
}

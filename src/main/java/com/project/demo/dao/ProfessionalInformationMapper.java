package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.ProfessionalInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 专业信息：(ProfessionalInformation)Mapper接口
 *
 */
@Mapper
public interface ProfessionalInformationMapper extends BaseMapper<ProfessionalInformation>{

        List<String> getNameList();
}

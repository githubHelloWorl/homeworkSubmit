package com.project.demo.service;

import com.project.demo.dao.ProfessionalInformationMapper;
import com.project.demo.entity.ProfessionalInformation;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专业信息：(ProfessionalInformation)表服务接口
 *
 */
@Service
public class ProfessionalInformationService extends BaseService<ProfessionalInformation> {

    @Autowired
    private ProfessionalInformationMapper professionalInformationMapper;

    public List<String> getNameList() {
        return professionalInformationMapper.getNameList();
    }

}

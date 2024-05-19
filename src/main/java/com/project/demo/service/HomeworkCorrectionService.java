package com.project.demo.service;

import com.project.demo.dao.HomeworkCorrectionMapper;
import com.project.demo.entity.HomeworkCorrection;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作业批改：(HomeworkCorrection)表服务接口
 *
 */
@Service
public class HomeworkCorrectionService extends BaseService<HomeworkCorrection> {

    @Autowired
    private HomeworkCorrectionMapper homeworkCorrectionMapper;

    public List<String> getNameList(){
        return homeworkCorrectionMapper.getNameList();
    }
}

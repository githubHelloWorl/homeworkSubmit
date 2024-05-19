package com.project.demo.service;

import com.project.demo.dao.HomeworkCorrectionMapper;
import com.project.demo.dao.HomeworkSubmissionMapper;
import com.project.demo.entity.HomeworkSubmission;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作业提交：(HomeworkSubmission)表服务接口
 *
 */
@Service
public class HomeworkSubmissionService extends BaseService<HomeworkSubmission> {


    @Autowired
    private HomeworkSubmissionMapper homeworkSubmissionMapper;

    public String updateSubmissionStatus(Integer id){
        Integer res = homeworkSubmissionMapper.updateSubmissionStatus(id);
        if (res == 1) {
            return "批改成功！";
        }
        return "";
    }
}

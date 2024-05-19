package com.project.demo.service;

import com.project.demo.dao.AssignmentInformationMapper;
import com.project.demo.entity.AssignmentInformation;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作业信息：(AssignmentInformation)表服务接口
 *
 */
@Service
public class AssignmentInformationService extends BaseService<AssignmentInformation> {

    @Autowired
    private AssignmentInformationMapper assignmentInformationMapper;

    public List<Map<String,Object>> getList(Integer page,Integer size){
        int startIndex = (page - 1) * size;
        return assignmentInformationMapper.getList(startIndex,size);
    }

    public List<Map<String,Object>> getStudent(Integer userId) {
        return assignmentInformationMapper.getStudentAss(userId);
    }

    public String toPublic(Integer id){
        int res = assignmentInformationMapper.toPublic(id);
        if (res == 1) {
            return "发布成功！";
        }
        return "";
    }
}

package com.project.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.demo.dao.LearningResourceMapper;
import com.project.demo.entity.LearningResource;
import com.project.demo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学习资源：(LearningResource)表服务接口
 *
 */
@Service
public class LearningResourceService extends BaseService<LearningResource> {

    @Autowired
    private LearningResourceMapper learningResourceMapper;

    public List<LearningResource> getList(Integer page, Integer size, Integer userId) {
        Integer startIndex = (page - 1) * size;
        return learningResourceMapper.getList(startIndex,size,userId);
    }

    public List<String> getList(Integer userId){
        System.out.println(userId);
        return learningResourceMapper.getNameList(userId);
    }

//    // TODO
//    public List<String> getListTest(){
//        QueryWrapper<LearningResource> queryWrapper = new QueryWrapper<>();
//        learningResourceMapper.selectList(queryWrapper);
//        return learningResourceMapper.getNameList(userId);
//    }
}

package com.project.demo.controller;

import com.project.demo.entity.LearningResource;
import com.project.demo.service.LearningResourceService;
import com.project.demo.controller.base.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 学习资源：(LearningResource)表控制层
 *
 */
@RestController
@RequestMapping("/learning_resource")
public class LearningResourceController extends BaseController<LearningResource, LearningResourceService> {

    /**
     * 学习资源对象
     */
    @Autowired
    public LearningResourceController(LearningResourceService service) {
        setService(service);
    }

    @Autowired
    private LearningResourceService learningResourceService;



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

    @GetMapping("/getList")
    public List<LearningResource> getList(@PathParam("page")Integer page,@PathParam("size")Integer size,@PathParam("userId")Integer userId){
        System.out.println(page+" "+size+" "+userId);
        return learningResourceService.getList(page,size,userId);
    };

    @GetMapping("/getNameList")
    public List<String> getList(@PathParam("userId") Integer userId){
        System.out.println(userId);
        return learningResourceService.getList(userId);
    }

}

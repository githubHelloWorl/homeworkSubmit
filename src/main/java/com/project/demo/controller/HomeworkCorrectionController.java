package com.project.demo.controller;

import com.project.demo.entity.HomeworkCorrection;
import com.project.demo.service.HomeworkCorrectionService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 作业批改：(HomeworkCorrection)表控制层
 *
 */
@RestController
@RequestMapping("/homework_correction")
public class HomeworkCorrectionController extends BaseController<HomeworkCorrection, HomeworkCorrectionService> {

    /**
     * 作业批改对象
     */
    @Autowired
    public HomeworkCorrectionController(HomeworkCorrectionService service) {
        setService(service);
    }

    @Autowired
    private HomeworkCorrectionService service;


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

    @GetMapping("/getNameList")
    public List<String> getNameList(){
        return service.getNameList();
    }


}

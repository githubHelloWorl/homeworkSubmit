package com.project.demo.controller;

import com.project.demo.entity.HomeworkSubmission;
import com.project.demo.service.HomeworkSubmissionService;
import com.project.demo.controller.base.BaseController;
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
 * 作业提交：(HomeworkSubmission)表控制层
 *
 */
@RestController
@RequestMapping("/homework_submission")
public class HomeworkSubmissionController extends BaseController<HomeworkSubmission, HomeworkSubmissionService> {

    /**
     * 作业提交对象
     */
    @Autowired
    public HomeworkSubmissionController(HomeworkSubmissionService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

    @GetMapping("/updateSubmissionStatus")
    public String updateSubmissionStatus(@PathParam("id")Integer id){
        return service.updateSubmissionStatus(id);
    }


}

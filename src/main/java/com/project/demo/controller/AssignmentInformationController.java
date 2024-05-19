package com.project.demo.controller;

import com.project.demo.entity.AssignmentInformation;
import com.project.demo.service.AssignmentInformationService;
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
 * 作业信息：(AssignmentInformation)表控制层
 *
 */
@RestController
@RequestMapping("/assignment_information")
public class AssignmentInformationController extends BaseController<AssignmentInformation, AssignmentInformationService> {

    /**
     * 作业信息对象
     */
    @Autowired
    public AssignmentInformationController(AssignmentInformationService service) {
        setService(service);
    }

    @Autowired
    private AssignmentInformationService assignmentInformationService;



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

    @GetMapping("/getList")
    public List<Map<String,Object>> getList(@PathParam("page")Integer page,@PathParam("size")Integer size) {
        return assignmentInformationService.getList(page,size);
    }

    @GetMapping("/getStudentAss")
    public List<Map<String,Object>> getStudentAss(@PathParam("userId")Integer userId){
        return assignmentInformationService.getStudent(userId);
    }

    @GetMapping("/public")
    public String toPublic(@PathParam("id")Integer id){
        return assignmentInformationService.toPublic(id);
    }


}

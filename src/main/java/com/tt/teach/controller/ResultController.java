package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/res")
public class ResultController {
    @Resource
    private ResultService resultService;

    @RequestMapping("/result")
    public String result() {
        return "/result/result";
    }
    //查询所有
    @RequestMapping(value = "/getResultList",method = RequestMethod.GET)
    @ResponseBody
    public Object getStudentList() {
        List<Student> list=resultService.getResultList();
        return list;
    }
}

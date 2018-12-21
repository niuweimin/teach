package com.tt.teach.controller;

import com.tt.teach.pojo.Grade;
import com.tt.teach.pojo.Subject;
import com.tt.teach.service.SubjectService;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("sub")
public class SubjectController {
    @Resource
    private SubjectService subjectService;

    @RequestMapping("/subject")
    public String subject(Model model) {
        //查询年级表
        List<Grade> list=subjectService.getGrade();
        model.addAttribute("gradeList",list);
        return "/subject/subject";
    }
    //查询课程表的所有信息
    @RequestMapping(value = "/getSubjectList",method = RequestMethod.GET)
    @ResponseBody
    public Object getSubjectList() {
        List<Subject> list=subjectService.getSubjectList();
        return list;
    }
    //根据编号删除课程信息
    @RequestMapping(value = "/deleteSubject/{subjectNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteSubject(@PathVariable Integer subjectNo) {
        int result=subjectService.deleteSubject(subjectNo);
        if (result>0){
            return JsonResult.ok("删除成功！",result);
        }
        return JsonResult.no("删除失败！",result);
    }
    //添加课程表信息
    @RequestMapping(value = "/addSubject",method = RequestMethod.PUT)
    @ResponseBody
    public JsonResult addSubject(@RequestParam String subjectName, @RequestParam Integer classHour, @RequestParam Integer gradeID) {
        Subject subject=new Subject();
        subject.setSubjectName(subjectName);
        subject.setClassHour(classHour);
        subject.setGradeID(gradeID);
        int result=subjectService.addSubject(subject);
        if (result>0){
            return JsonResult.ok("添加成功！",result);
        }
        return JsonResult.no("添加失败！",result);
    }
    //修改课程表信息
    @RequestMapping(value = "/updateSub",method = RequestMethod.PUT)
    @ResponseBody
    public Object updateSub(@RequestParam Integer subjectNo,@RequestParam Integer classHour,@RequestParam String subjectName) {
        Subject subject=new Subject();
        subject.setClassHour(classHour);
        subject.setSubjectName(subjectName);
        subject.setSubjectNo(subjectNo);
        int result=subjectService.updateSubject(subject);
        if(result>0){
            return JsonResult.ok("修改成功",result);
        }else{
            return JsonResult.ok("修改失败",result);
        }

    }
    //查询是否有该课程
    @RequestMapping(value = "/getSubByName/{subjectName}",method = RequestMethod.GET)
    @ResponseBody
    public Object getSubByName(@PathVariable String subjectName) {
        Subject subject=subjectService.getSubByName(subjectName);
        if(subject!=null){
            return JsonResult.ok("有该课程",subject);
        }
        return JsonResult.no("没有该课程",subject);
    }
}

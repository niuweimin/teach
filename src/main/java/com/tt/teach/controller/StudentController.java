package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import com.tt.teach.utils.BaseController;
import com.tt.teach.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController extends BaseController{
    @Resource
    private StudentService studentService;
    //http://localhost:8090/stu/login
    //进入登录的请求
    @RequestMapping("/login")
    public String login() {
        return "/student/login";
    }
    //http://localhost:8090/stu/index
    @RequestMapping("/index")
    public String index() {
        //如果没有获取则不能进入，返回继续登录
        String studentName= (String) getSession().getAttribute(SESSION_KEY);
        if (studentName!=null){
            return "/student/index";
        }
        return REDIRECT+"/stu/login";
    }
    @RequestMapping("/student")
    public String student() {
        return "/student/student";
    }
    //验证登录的方法
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin() {
        String xuehao=getRequest().getParameter("studentNo");
        Integer studentNo=Integer.parseInt(xuehao);
        String loginPwd=getRequest().getParameter("loginPwd");
        Student student=new Student();
        student.setLoginPwd(loginPwd);
        student.setStudentNo(studentNo);
        Student student1=studentService.doLogin(student);
        if (student1!=null){
            //登陆成功获取用户名
            getSession().setAttribute(SESSION_KEY,student1.getStudentName());
            return FORWARD+"/stu/index";
        }
        return REDIRECT+"/stu/login";
    }
    //注销
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SESSION_KEY);
        return REDIRECT+"/stu/login";
    }
    //查询所有
    @RequestMapping(value = "/getStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Object getStudentList() {
        List<Student> list=studentService.getStudentList();
        return list;
    }
    //删除,使用delete提交
    @RequestMapping(value = "/deleteStudent/{stuNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteStudent(@PathVariable Integer stuNo) {
        int result=studentService.deleteStudent(stuNo);
        if (result>0){
            return JsonResult.ok("删除成功",result);
        }
        return JsonResult.no("删除失败",result);
    }
    //修改
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    public String updateStudent() {
        String xuehao=getRequest().getParameter("stuNo");
        Integer stuNo=Integer.parseInt(xuehao);
        String stuName=getRequest().getParameter("stuName");
        String stuPwd=getRequest().getParameter("stuPwd");
        String stuPhone=getRequest().getParameter("stuPhone");
        Student student=new Student();
        student.setStudentNo(stuNo);
        student.setStudentName(stuName);
        student.setLoginPwd(stuPwd);
        student.setPhone(stuPhone);
        int result=studentService.updateStudent(student);
        if (result>0){
            return FORWARD+"/stu/student";
        }
        return FORWARD+"/stu/student";
    }

    //ajax异步请求
    @RequestMapping(value = "/getStuByNo/{studentNo}",method = RequestMethod.GET)
    @ResponseBody
    public Object getStuByNo(@PathVariable Integer studentNo) {
        Student student=studentService.getstuByNo(studentNo);
        if(student!=null){
            return JsonResult.ok("有该学生",student);
        }
        return JsonResult.no("没有该学生",student);
    }

}

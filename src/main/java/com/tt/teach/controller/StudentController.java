package com.tt.teach.controller;

import com.tt.teach.pojo.Student;
import com.tt.teach.service.StudentService;
import com.tt.teach.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    //删除
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST)
    public String deleteStudent() {
        return "明见";
    }
}

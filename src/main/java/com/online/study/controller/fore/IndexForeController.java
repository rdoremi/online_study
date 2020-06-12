package com.online.study.controller.fore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fore")
public class IndexForeController {

    @RequestMapping("/login")
    public String indexFore(){
        return "fore/index";
    }
    @RequestMapping("/register")
    public String register(){
        return "fore/register";
    }

    @RequestMapping("/course")
    public String course(){
        return "fore/page/course";
    }
    @RequestMapping("/discuss")
    public String discuss(){
        return "fore/page/discuss";
    }
    @RequestMapping("/my_class")
    public String myClass(){
        return "/fore/page/my-class";
    }
    @RequestMapping("/exam_work")
    public String examWork(){
        return "/fore/page/exam-work";
    }
    @RequestMapping("/read")
    public String read(){
        return "/fore/page/read";
    }
    @RequestMapping("/my_university")
    public String myUniversity(){
        return "/fore/page/my-university";
    }
    @RequestMapping("/classroom")
    public String classroom(){
        return "/fore/page/classroom";
    }
    @RequestMapping("/teach")
    public String teach(){
        return "/fore/page/classroom-teach";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "/fore/page/edu/admin";
    }
    @RequestMapping("/mycollege")
    public String mycollege(){
        return "/fore/page/my-college";
    }
    @RequestMapping("/cadmin")
    public String collegeadmin(){
        return "/fore/page/edu/college-admin";
    }
    @RequestMapping("/teachcourse")
    public String teachcourse(){
        return "/fore/page/edu/teacher-course";
    }
    @RequestMapping("/adminclass")
    public String adminClass(){
        return "/fore/page/edu/my-class";
    }
    @RequestMapping("/myinfo")
    public String myinfo(){
        return "/fore/page/my-info";
    }
}

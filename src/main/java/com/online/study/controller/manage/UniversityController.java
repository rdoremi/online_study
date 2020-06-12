package com.online.study.controller.manage;

import com.online.study.common.ServerResponse;
import com.online.study.entity.College;
import com.online.study.entity.University;
import com.online.study.service.CollegeService;
import com.online.study.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;



    @PostMapping("/add")
    public ServerResponse add(University university){
        return universityService.addUniversity(university);
    }

    @GetMapping("/get_all_list")
    public ServerResponse getAllList(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "limit",defaultValue = "10") int limit){
        return universityService.getList(page,limit);
    }
    @GetMapping("/get_list")
    public ServerResponse getList(){
        return universityService.getUniversity();
    }


    @PostMapping("/add_college")
    public ServerResponse addCollege(College college){
        return universityService.addCollege(college);
    }
}

package m2.info.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("teacher/")
public class TeacherController {

    @RequestMapping("/")
    public String displayTeacherPanel() { return "teacher"; }

}

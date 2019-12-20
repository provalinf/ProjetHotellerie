package m2.info.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/")
public class StudentController {

    @RequestMapping("/")
    public String displayStudentPanel() { return "student"; }

}

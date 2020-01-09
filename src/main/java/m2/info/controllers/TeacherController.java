package m2.info.controllers;

import m2.info.models.user.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends UserController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        String id = getIdUser(request);
        Teacher teacher = userManager.getTeacher(id);
        model.addAttribute("modules", teacher.getModules());

        return "teacher/home";
    }

}

package m2.info.controllers;

import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.services.user.IUserManagement;
import m2.info.services.user.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private IUserManagement userManager;

    @GetMapping("student")
    public String homeStudent(HttpServletRequest request, Principal principal, Model model) {

        String id = getIdUser(request, principal);
        Student student = userManager.getStudent(id);
        model.addAttribute("modules", student.getModules());

        return "student/home";
    }

    @GetMapping("teacher")
    public String homeTeacher(HttpServletRequest request, Principal principal, Model model) {

        String id = getIdUser(request, principal);
        Teacher teacher = userManager.getTeacher(id);
        model.addAttribute("modules", teacher.getModules());

        return "teacher/home";
    }

    public String getIdUser(HttpServletRequest request, Principal principal) {
        final String ID = "id";

        if (request.getSession().getAttribute(ID) == null) {
            String id = ((UserManagement) userManager).loadUserByUsername(principal.getName()).getId();
            request.getSession().setAttribute(ID, id);
        }

        return request.getSession().getAttribute(ID).toString();
    }

}

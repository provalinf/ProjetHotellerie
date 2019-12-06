package m2.info.controllers;

import m2.info.entities.User;
import m2.info.services.IUserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("users")
public class UserController {

    private final static int TEACHER = 1;
    private final static int STUDENT = 2;

    @Autowired private IUserManagment userManager;

    @RequestMapping("/")
    public String showPage(Model model) {
        model.addAttribute("students", userManager.getAllStudents());
        model.addAttribute("teachers", userManager.getAllTeachers());
        return "user_view";
    }

    @RequestMapping("add")
    public String addUser(Model model,
                          @RequestParam(value="userId") String userId,
                          @RequestParam(value="lastName") String lastName,
                          @RequestParam(value="firstName") String firstName,
                          @RequestParam(value="role") Integer role)
    {

        if (role == TEACHER) userManager.addTeacher(userId, lastName, firstName);
        else userManager.addStudent(userId, lastName, firstName);

        return showPage(model);
    }
}


package m2.info.controllers;

import m2.info.models.user.Authorities;
import m2.info.services.module.IModuleManagment;
import m2.info.services.user.IUserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired private IUserManagement userManager;
    @Autowired private IModuleManagment moduleManager;

    @GetMapping("/")
    public String displayAdminPanel() { return "admin/home"; }

    @GetMapping("users")
    public String displayUsers(Model model) {
        model.addAttribute("students", userManager.getAllStudents());
        model.addAttribute("teachers", userManager.getAllTeachers());
        return "admin/users";
    }

    @PostMapping("users")
    public String addUser(Model model,
                          @RequestParam(value="id") String id,
                          @RequestParam(value="lastname") String lastname,
                          @RequestParam(value="firstname") String firstname,
                          @RequestParam(value="authority") String authority) {

        final int endIndex = (lastname.length() < 8) ? lastname.length() : 7;
        final String username = firstname.charAt(0) + lastname.substring(0, endIndex);

        if (authority.equals(Authorities.STUDENT.name()))
            userManager.addStudent(id, username, "mdp_" + username, lastname, firstname);
        else    if (authority.equals(Authorities.TEACHER.name()))
                    userManager.addTeacher(id, username, "mdp_" + username, lastname, firstname);
                else throw new IllegalStateException();

        return displayUsers(model);
    }

    @GetMapping("student/{userId}")
    public String displayStudent(Model model, @PathVariable String userId) {
        model.addAttribute("student", userManager.getStudent(userId));
        return "admin/student";
    }

    @GetMapping("teacher/{userId}")
    public String displayTeacher(Model model, @PathVariable String userId) {
        model.addAttribute("teacher", userManager.getTeacher(userId));
        return "admin/teacher";
    }

    @GetMapping("modules")
    public String displayModules(Model model) {
        model.addAttribute("modules", moduleManager.getAllModules());
        return "admin/modules";
    }

    @GetMapping("module/{moduleId}")
    public String displayModule(Model model, @PathVariable String moduleId) {
        model.addAttribute("module", moduleManager.getModule(moduleId));
        return "admin/module";
    }

    @PostMapping("modules/add")
    public String addModule(Model model,
                            @RequestParam(value="verboseName") String verboseName,
                            @RequestParam(value="label") String label,
                            @RequestParam(value="description") String description) {

        moduleManager.addModule(verboseName, label, description);
        return displayModules(model);
    }
}
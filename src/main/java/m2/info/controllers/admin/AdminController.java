package m2.info.controllers.admin;

import m2.info.models.user.Authorities;
import m2.info.services.module.IModuleManagment;
import m2.info.services.user.IUserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired private IUserManagement userManager;
    @Autowired private IModuleManagment moduleManager;

    @RequestMapping("/")
    public String displayAdminPanel(Model model) { return "admin"; }

    @RequestMapping("users/")
    public String displayUser(Model model) {
        model.addAttribute("students", userManager.getAllStudents());
        model.addAttribute("teachers", userManager.getAllTeachers());
        return "user_view";
    }

    @RequestMapping("users/add")
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

        return displayUser(model);
    }

    @RequestMapping("modules/")
    public String displayModule(Model model) {
        model.addAttribute("modules", moduleManager.getAllModules());
        return "module_view";
    }

    @RequestMapping("modules/add")
    public String addModule(Model model,
                            @RequestParam(value="verboseName") String verboseName,
                            @RequestParam(value="label") String label,
                            @RequestParam(value="description") String description) {

        moduleManager.addModule(verboseName, label, description);
        return displayModule(model);
    }
}
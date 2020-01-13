package m2.info.controllers;

import m2.info.models.Module;
import m2.info.models.user.Authorities;
import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.services.module.IModuleManagement;
import m2.info.services.user.IUserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired private IUserManagement userManager;
    @Autowired private IModuleManagement moduleManager;

    @GetMapping("/")
    public String home() { return "admin/home"; }

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

        String username = createUsername(lastname, firstname);

        if (authority.equals(Authorities.STUDENT.name()))
            userManager.addStudent(new Student(id, username, "mdp_" + username, lastname, firstname));
        else    if (authority.equals(Authorities.TEACHER.name()))
                    userManager.addTeacher(new Teacher(id, username, "mdp_" + username, lastname, firstname));
                else throw new IllegalStateException();

        return displayUsers(model);
    }

    @GetMapping("student/{userId}")
    public String displayStudent(Model model, @PathVariable String userId) {
        Student student = userManager.getStudent(userId);
        model.addAttribute("modules", moduleManager.getAllModules());
        model.addAttribute("student", student);
        model.addAttribute("linked_modules", student.getModules());
        return "admin/student";
    }

    @PostMapping("student/{userId}")
    public String addStudentModule(Model model,
                                   @PathVariable String userId,
                                   @RequestParam(value="module") Integer idModule) {
        Student student = userManager.getStudent(userId);
        student.addModule(moduleManager.getModule(idModule.longValue()));
        userManager.updateStudent(userId, student);
        return displayStudent(model, userId);
    }

    @GetMapping("student/{userId}/delete")
    public RedirectView deleteStudent(Model model, @PathVariable String userId) {
        userManager.deleteStudent(userId);
        return new RedirectView("/admin/users");
    }

    @GetMapping("teacher/{userId}")
    public String displayTeacher(Model model, @PathVariable String userId) {
        Teacher teacher = userManager.getTeacher(userId);
        model.addAttribute("modules", moduleManager.getAllModules());
        model.addAttribute("teacher", teacher);
        model.addAttribute("linked_modules", teacher.getModules());
        return "admin/teacher";
    }

    @PostMapping("teacher/{userId}")
    public String addTeachertModule(Model model,
                                   @PathVariable String userId,
                                   @RequestParam(value="module") Integer idModule) {
        Teacher teacher = userManager.getTeacher(userId);
        teacher.addModule(moduleManager.getModule(idModule.longValue()));
        userManager.updateTeacher(userId, teacher);
        return displayTeacher(model, userId);
    }

    @GetMapping("teacher/{userId}/delete")
    public RedirectView deleteTeacher(Model model, @PathVariable String userId) {
        userManager.deleteTeacher(userId);
        return new RedirectView("/admin/users");
    }

    @GetMapping("modules")
    public String displayModules(Model model) {
        model.addAttribute("modules", moduleManager.getAllModules());
        return "admin/modules";
    }

    @GetMapping("module/{moduleId}")
    public String displayModule(Model model, @PathVariable Integer moduleId) {
        model.addAttribute("module", moduleManager.getModule(moduleId.longValue()));
        return "admin/module";
    }

    @GetMapping("module/{moduleId}/delete")
    public RedirectView deleteModule(Model model, @PathVariable long moduleId) {
        moduleManager.deleteModule(moduleId);
        return new RedirectView("/admin/modules");
    }

    @PostMapping("modules")
    public String addModule(Model model,
                            @RequestParam(value="verboseName") String verboseName,
                            @RequestParam(value="label") String label,
                            @RequestParam(value="description") String description) {

        moduleManager.addModule(new Module(verboseName, label, description));
        return displayModules(model);
    }

    private String createUsername(String lastname, String firstname) {

        final int endIndex = (lastname.length() < 8) ? lastname.length() : 7;
        String s = firstname.charAt(0) + lastname.substring(0, endIndex), username;

        int i = 1;
        boolean validUsername = false;

        s = s.toLowerCase();
        s = s.replaceAll("à", "a");
        s = s.replaceAll("[éèêë]", "e");
        s = s.replaceAll("-", "");

        do {
            username = s + i;
            try {
                userManager.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                validUsername = true;
                continue;
            }
            ++i;
        } while (!validUsername);

        return username;
    }
}
package m2.info.controllers;

import m2.info.models.Module;
import m2.info.models.user.Authorities;
import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.models.user.User;
import m2.info.services.module.IModuleManagement;
import m2.info.services.user.IUserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired private IUserManagement userManager;
    @Autowired private IModuleManagement moduleManager;

    @GetMapping("/")
    public String home() { return "admin/home"; }

    @GetMapping("user/{userId}")
    public String displayUser(Model model, @PathVariable String userId) {
        User user = userManager.getUser(userId);
        model.addAttribute("modules", moduleManager.getAllModules());
        model.addAttribute("user", user);
        model.addAttribute("linked_modules", user.getModules());
        return "admin/user";
    }

    @PostMapping("user/{userId}")
    public RedirectView linkModule(Model model, @PathVariable String userId,  @RequestParam(value="module") long idModule) {
        User user = userManager.getUser(userId);
        user.addModule(moduleManager.getModule(idModule));
        userManager.saveUser(user);
        return new RedirectView("/admin/users");
    }

    @GetMapping("user/{userId}/delete")
    public RedirectView deleteUser(Model model, @PathVariable String userId) {
        userManager.deleteUser(userId);
        return new RedirectView("/admin/users");
    }

    @PostMapping("users")
    public String addUser(Model model,
                          @RequestParam(value="id") String id,
                          @RequestParam(value="lastname") String lastname,
                          @RequestParam(value="firstname") String firstname,
                          @RequestParam(value="authority") String authority) {

        String username = createUsername(lastname, firstname);

        if (authority.equals(Authorities.STUDENT.name()))
            userManager.saveUser(new Student(id, username, "mdp_" + username, lastname, firstname));
        else    if (authority.equals(Authorities.TEACHER.name()))
                    userManager.saveUser(new Teacher(id, username, "mdp_" + username, lastname, firstname));
                else throw new IllegalStateException();

        return displayUsers(model);
    }

    @GetMapping("users")
    public String displayUsers(Model model) {
        model.addAttribute("users", userManager.getAllUsers());
        return "admin/users";
    }

    @GetMapping("modules/")
    public String displayModules(Model model) {
        model.addAttribute("modules", moduleManager.getAllModules());
        return "admin/modules";
    }

    @GetMapping("module/{moduleId}")
    public String displayModule(Model model, @PathVariable long moduleId) {
        Module module = moduleManager.getModule(moduleId);
        Set<Student> students = new HashSet<>();
        Set<Teacher> teachers = new HashSet<>();

        for (User user : module.getUsers())
            if (user instanceof Student) students.add((Student)user);
            else teachers.add((Teacher)user);

        model.addAttribute("module", module);
        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);

        return "admin/module";
    }

    @PostMapping("modules")
    public String addModule(Model model,
                            @RequestParam(value="verboseName") String verboseName,
                            @RequestParam(value="label") String label,
                            @RequestParam(value="description") String description) {

        moduleManager.saveModule(new Module(verboseName, label, description));
        return displayModules(model);
    }

    @PostMapping("module/{moduleId}")
    public RedirectView updateModule(Model model, @PathVariable long moduleId,
                                     @RequestParam(value="verboseName") String verboseName,
                                     @RequestParam(value="label") String label,
                                     @RequestParam(value="description") String description) {
        Module module= moduleManager.getModule(moduleId);
        module.setVerboseName(verboseName);
        module.setLabel(label);
        module.setDescription(description);
        moduleManager.saveModule(module);
        return new RedirectView("/admin/modules/");
    }

    @GetMapping("module/{moduleId}/delete")
    public RedirectView deleteModule(Model model, @PathVariable long moduleId) {
        moduleManager.deleteModule(moduleId);
        return new RedirectView("/admin/modules/");
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
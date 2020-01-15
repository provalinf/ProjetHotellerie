package m2.info.controllers;

import m2.info.models.Evaluation;
import m2.info.models.Module;
import m2.info.models.user.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends UserController {

    @GetMapping("")
    public String home(HttpServletRequest request, Model model) {
        String id = getIdUser(request);
        Teacher teacher = userManager.getTeacher(id);

        model.addAttribute("teacher", teacher);
        model.addAttribute("modules", teacher.getModules());

        return "teacher/home";
    }

    @GetMapping("/evaluations/{moduleId}")
    public String displayModuleEvals(Model model, @PathVariable long moduleId) {
        Module module = moduleManager.getModule(moduleId);
        Set<Evaluation> evals = module.getEvaluations();

        model.addAttribute("module", module);
        model.addAttribute("evals", evals);

        return "teacher/evals";
    }

}

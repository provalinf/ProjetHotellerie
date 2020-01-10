package m2.info.controllers;

import m2.info.models.Evaluation;
import m2.info.models.Module;
import m2.info.models.user.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("/student")
public class StudentController extends UserController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        String id = getIdUser(request);
        Student student = userManager.getStudent(id);
        model.addAttribute("modules", getNonEvaluatedModules(student));
        model.addAttribute("evals", student.getEvaluations());

        return "student/home";
    }

    @PostMapping("/")
    public String addEval(HttpServletRequest request, Model model,
                          @RequestParam(value="module") long moduleId,
                          @RequestParam(value="consistency") short consistency,
                          @RequestParam(value="documentation") short documentation,
                          @RequestParam(value="lecture") short lecture,
                          @RequestParam(value="personnalInterest") short personnalInterest,
                          @RequestParam(value="practicalWork") short practicalWork,
                          @RequestParam(value="tutorial") short tutorial,
                          @RequestParam(value="workload") short workload,
                          @RequestParam(value="comment") String comment) {

        Student student = userManager.getStudent(getIdUser(request));
        Module module = moduleManager.getModule(moduleId);
        Evaluation eval = new Evaluation(consistency, documentation, lecture, personnalInterest, practicalWork, tutorial, workload, comment, student, module);
        evalManager.addEvaluation(eval);

        return home(request, model);
    }

    private Set<Module> getNonEvaluatedModules(Student student) {
        Set<Module> modules = student.getModules();

        for (Evaluation eval : student.getEvaluations())
            modules.remove(eval.getModule());

        return modules;
    }

}

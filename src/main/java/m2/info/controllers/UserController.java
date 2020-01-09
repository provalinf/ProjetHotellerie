package m2.info.controllers;

import m2.info.models.Evaluation;
import m2.info.models.Module;
import m2.info.models.user.Student;
import m2.info.models.user.Teacher;
import m2.info.services.evaluation.IEvalManagement;
import m2.info.services.module.IModuleManagement;
import m2.info.services.user.IUserManagement;
import m2.info.services.user.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired private IEvalManagement evalManager;
    @Autowired private IModuleManagement moduleManager;
    @Autowired private IUserManagement userManager;

    @GetMapping("student")
    public String homeStudent(HttpServletRequest request, Model model) {

        String id = getIdUser(request);
        Student student = userManager.getStudent(id);
        model.addAttribute("modules", student.getModules());
        model.addAttribute("evals", student.getEvaluations());

        return "student/home";
    }

    @PostMapping("student")
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

        return homeStudent(request, model);
    }

    @GetMapping("teacher")
    public String homeTeacher(HttpServletRequest request, Model model) {

        String id = getIdUser(request);
        Teacher teacher = userManager.getTeacher(id);
        model.addAttribute("modules", teacher.getModules());

        return "teacher/home";
    }

    public String getIdUser(HttpServletRequest request) {
        final String ID = "id";

        if (request.getSession().getAttribute(ID) == null) {
            String id = ((UserManagement) userManager).loadUserByUsername(request.getUserPrincipal().getName()).getId();
            request.getSession().setAttribute(ID, id);
        }

        return request.getSession().getAttribute(ID).toString();
    }

}

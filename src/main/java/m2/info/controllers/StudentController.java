package m2.info.controllers;

import m2.info.models.Evaluation;
import m2.info.models.Module;
import m2.info.models.user.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Set;

@Controller
@RequestMapping("/student")
public class StudentController extends UserController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        String id = getIdUser(request);
        Student student = userManager.getStudent(id);

        model.addAttribute("student", student);
        model.addAttribute("modules", getNonEvaluatedModules(student));
        model.addAttribute("evals", student.getEvaluations());

        return "student/home";
    }

    @PostMapping("")
    public String addEval(HttpServletRequest request, Model model,
                          @RequestParam(value="module") long moduleId,
                          @RequestParam(value="consistency") short consistency,
                          @RequestParam(value="documentation") short documentation,
                          @RequestParam(value="lecture") short lecture,
                          @RequestParam(value="personalInterest") short personalInterest,
                          @RequestParam(value="practicalWork") short practicalWork,
                          @RequestParam(value="tutorial") short tutorial,
                          @RequestParam(value="workload") short workload,
                          @RequestParam(value="comment") String comment) {

        Student student = userManager.getStudent(getIdUser(request));
        Module module = moduleManager.getModule(moduleId);

        Iterable <Evaluation> evals = evalManager.getAllEvaluations();
        boolean alreadyEvaluated = false;

        Iterator<Evaluation> iterator = evals.iterator();
        Evaluation eval;

        while (iterator.hasNext() && !alreadyEvaluated) {
            eval = iterator.next();
            if (eval.getModule().getId() == moduleId && eval.getAuthor().getId().equals(student.getId()))
                alreadyEvaluated = true;
        }

        if (!alreadyEvaluated) {
            eval = new Evaluation(consistency, documentation, lecture, personalInterest, practicalWork, tutorial, workload, comment, student, module);
            evalManager.saveEvaluation(eval);
        }

        return home(request, model);
    }

    @GetMapping("evaluation/{evalId}")
    public String displayEval(Model model, @PathVariable long evalId) {
        Evaluation eval = evalManager.getEvaluation(evalId);
        model.addAttribute("module", eval.getModule());
        model.addAttribute("eval", eval);
        return "student/eval";
    }

    @PostMapping("evaluation/{evalId}")
    public RedirectView updateEval(Model model, @PathVariable long evalId,
                                    @RequestParam(value="consistency") short consistency,
                                    @RequestParam(value="documentation") short documentation,
                                    @RequestParam(value="lecture") short lecture,
                                    @RequestParam(value="personalInterest") short personalInterest,
                                    @RequestParam(value="practicalWork") short practicalWork,
                                    @RequestParam(value="tutorial") short tutorial,
                                    @RequestParam(value="workload") short workload,
                                    @RequestParam(value="comment") String comment) {
        Evaluation eval = evalManager.getEvaluation(evalId);
        eval.setConsistency(consistency);
        eval.setDocumentation(documentation);
        eval.setLecture(lecture);
        eval.setPersonalInterest(personalInterest);
        eval.setPracticalWork(practicalWork);
        eval.setTutorial(tutorial);
        eval.setWorkload(workload);
        eval.setComment(comment);
        evalManager.saveEvaluation(eval);
        return new RedirectView("/student/evaluation/" + evalId);
    }

    @GetMapping("evaluation/{evalId}/delete")
    public RedirectView deleteEval(Model model, @PathVariable long evalId) {
        evalManager.deleteEvaluation(evalId);
        return new RedirectView("/student/");
    }

    private Set<Module> getNonEvaluatedModules(Student student) {
        Set<Module> modules = student.getModules();

        for (Evaluation eval : student.getEvaluations())
            modules.remove(eval.getModule());

        return modules;
    }
}

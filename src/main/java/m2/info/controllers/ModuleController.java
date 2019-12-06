package m2.info.controllers;

import m2.info.services.module.IModuleManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("modules")
public class ModuleController {

    @Autowired private IModuleManagment moduleManager;

    @RequestMapping("/")
    public String display(Model model) {
        model.addAttribute("modules", moduleManager.getAllModules());
        return "module_view";
    }

    @RequestMapping("add")
    public String addModule(Model model,
                          @RequestParam(value="verboseName") String verboseName,
                          @RequestParam(value="label") String label,
                          @RequestParam(value="description") String description)
    {

        moduleManager.addModule(verboseName, label, description);
        return display(model);
    }
}


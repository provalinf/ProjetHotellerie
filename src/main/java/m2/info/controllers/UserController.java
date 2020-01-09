package m2.info.controllers;

import m2.info.services.evaluation.IEvalManagement;
import m2.info.services.module.IModuleManagement;
import m2.info.services.user.IUserManagement;
import m2.info.services.user.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class UserController {

    @Autowired protected IEvalManagement evalManager;
    @Autowired protected IModuleManagement moduleManager;
    @Autowired protected IUserManagement userManager;

    protected String getIdUser(HttpServletRequest request) {
        final String ID = "id";

        if (request.getSession().getAttribute(ID) == null) {
            String id = ((UserManagement) userManager).loadUserByUsername(request.getUserPrincipal().getName()).getId();
            request.getSession().setAttribute(ID, id);
        }

        return request.getSession().getAttribute(ID).toString();
    }

}

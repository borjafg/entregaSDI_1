package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;

public class RegistrarseAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String login = (String) request.getParameter("login");
	String email = (String) request.getParameter("email");
	String password1 = (String) request.getParameter("password1");
	String password2 = (String) request.getParameter("password2");

	try {
	    UserService userServc = Services.getUserService();
	    userServc.registerUser(login, email, password1, password2);

	    request.setAttribute("exito_usuario", "El registro se ha "
		    + "completado");

	    return "EXITO"; // No se loguea automáticamente
	}

	catch (BusinessException be) {
	    request.setAttribute("advertencia_usuario", be.getMessage());

	    return "FRACASO";
	}
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
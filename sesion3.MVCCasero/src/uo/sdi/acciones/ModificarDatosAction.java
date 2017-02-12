package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String resultado = "EXITO";

	String nuevoEmail = request.getParameter("email");
	HttpSession session = request.getSession();

	User user = ((User) session.getAttribute("user"));

	String emailAnterior = user.getEmail();
	user.setEmail(nuevoEmail);

	try {
	    UserService userService = Services.getUserService();
	    userService.updateUserDetails(user);

	    Log.debug("Modificado email de [%s] con el valor [%s]",
		    user.getLogin(), nuevoEmail);
	    session.setAttribute("user", user);
	}

	catch (BusinessException b) {
	    Log.debug(
		    "Algo ha ocurrido actualizando el email de [%s] a [%s]: %s",
		    user.getLogin(), nuevoEmail, b.getMessage());
	    user.setEmail(emailAnterior); // Dejar el email sin cambiar

	    resultado = "FRACASO";
	}

	return resultado;
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
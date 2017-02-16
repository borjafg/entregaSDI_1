package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.business.Services;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ValidarseAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String login = request.getParameter("login");
	String password = request.getParameter("password");

	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");

	if (user == null) {
	    return procesarPeticionUsuarioAnonimo(request, session, login,
		    password); // Usuario anónimo que intenta hacer login
	}

	else if (!login.equals(user.getLogin())) {
	    return procesarPeticionUsuarioRegistrado(request, session, login,
		    password); // Usuario "identificado" que intenta hacer login
	}

	return "EXITO"; // Ya está logueado con ese usuario
    }

    private String procesarPeticionUsuarioAnonimo(HttpServletRequest request,
	    HttpSession session, String login, String password) {

	UserService userService = Services.getUserService();
	User userByLogin = null;

	try {
	    userByLogin = userService.findLoggableUser(login, password);
	}

	catch (BusinessException b) {
	    session.invalidate();

	    Log.debug("Algo ha ocurrido intentando iniciar sesión [%s]: %s",
		    login, b.getMessage());
	    request.setAttribute("mensajeParaElUsuario", b.getMessage());

	    return "FRACASO";
	}

	if (userByLogin != null) {
	    if (userByLogin.getPassword().equals(password)) {
		session.setAttribute("user", userByLogin);

		session.setAttribute("fechaInicioSesion", new java.util.Date());
		Log.info("El usuario [%s] ha iniciado sesión", login);

		return "EXITO";
	    }

	    else {
		session.setAttribute("user", userByLogin);

		session.setAttribute("mensajeParaElUsuario", "El usuario o la "
			+ "password indicada no son válidos");

		Log.info("El usuario '%s' o la password indicada no son "
			+ "válidos", login);

		return "EXITO";
	    }
	}

	else {
	    session.invalidate();

	    Log.info("El usuario '%s' no está registrado", login);
	    request.setAttribute("mensajeParaElUsuario", "El usuario '" + login
		    + "' no está registrado.");

	    return "FRACASO";
	}
    }

    private String procesarPeticionUsuarioRegistrado(
	    HttpServletRequest request, HttpSession session, String login,
	    String password) {

	Log.info(
		"Se ha intentado iniciar sesión como [%s] teniendo la sesión iniciada como [%s]",
		login, ((User) session.getAttribute("user")).getLogin());

	request.setAttribute("mensajeParaElUsuario",
		"Se ha intentado iniciar sesión como '" + login
			+ "' teniendo la sesión iniciada como '"
			+ ((User) session.getAttribute("user")).getLogin()
			+ "'");

	session.invalidate();

	return "FRACASO";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
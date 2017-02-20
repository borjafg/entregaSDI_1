package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.User;

public class ModificarDatosTareaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	boolean error = validarParametrosCategoria(request);

	if (error) {
	    return "FRACASO";
	}

	User user = (User) request.getSession().getAttribute("user");

	return null;
    }

    // =================================
    // Validaciones de parámetros
    // =================================

    private boolean validarParametrosCategoria(HttpServletRequest request) {
	String categoriaSistema = request.getParameter("CategoriaSistema");

	if (categoriaSistema == null) {
	    request.setAttribute("advertencia_usuario",
		    "no se han podido modificar los datos de la tarea porque "
			    + "falta indicar algún parámetro");

	    Log.error("La categoria del sistema es nula.");

	    return false;
	}

	else if (categoriaSistema.equals("NO")) {
	    String idCategoria = request.getParameter("idCategoria");

	    if (idCategoria != null) {
		request.setAttribute("idCategoria", idCategoria);
		return true;
	    }

	    request.setAttribute("advertencia_usuario",
		    "no se han podido modificar los datos de la tarea porque "
			    + "falta indicar algún parámetro");

	    return false;
	}

	else if (categoriaSistema.equals("SI")) {

	}

	request.setAttribute("advertencia_usuario",
		"no se han podido modificar los datos de la tarea porque "
			+ "los parámetros indicados no son válidas");

	return false;
    }
}
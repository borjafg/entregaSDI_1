package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

/**
 * Contiene unas validaciones comunes a las clases que permiten modificar los
 * datos de una tarea.
 * 
 */
public abstract class AbstractModificarDatosTareaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	if (parametrosCorrectosCategoria(request)) {
	    return procesarPeticion(request, response);
	}

	return "FRACASO";
    }

    protected abstract String procesarPeticion(HttpServletRequest request,
	    HttpServletResponse response);

    // =================================
    // Validaciones de parámetros
    // =================================

    private boolean parametrosCorrectosCategoria(HttpServletRequest request) {
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
		request.setAttribute("CategoriaSistema", "NO");
		return true;
	    }

	    request.setAttribute("advertencia_usuario",
		    "no se han podido modificar los datos de la tarea porque "
			    + "falta indicar algún parámetro");

	    return false;
	}

	else if (categoriaSistema.equals("SI")) {
	    String nombreCateg = request.getParameter("nombreCategoria");

	    if (nombreCateg != null) {
		request.setAttribute("nombreCategoria", nombreCateg);
		request.setAttribute("CategoriaSistema", "SI");
		return true;
	    }

	    request.setAttribute("advertencia_usuario",
		    "no se han podido modificar los datos de la tarea porque "
			    + "falta indicar algún parámetro");

	    return false;
	}

	request.setAttribute("advertencia_usuario",
		"no se han podido modificar los datos de la tarea porque "
			+ "los parámetros indicados no son válidas");

	return false;
    }

}
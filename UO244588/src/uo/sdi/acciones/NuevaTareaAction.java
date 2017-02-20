package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;
import alb.util.log.Log;

public class NuevaTareaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String categoriaSistema = validarCategoria(request);

	if (categoriaSistema == null) {
	    Log.debug("No se ha indicado correctamente si la categoria de la "
		    + "tarea a crear pertenece al sistema o al usuario");

	    request.setAttribute("advertencia_usuario",
		    "Alguno de los parámetros indicados no es válido");

	    return "FRACASO";
	}

	String nombreTarea = validarNombre(request.getParameter("nombreTarea"));

	try {
	    if (categoriaSistema.equals("SI")) {
		return crearTareaSistema(request, nombreTarea);
	    }

	    else { // Categoria del usuario
		return crearTareaUsuario(request, nombreTarea);
	    }
	}

	catch (BusinessException be) {
	    Log.debug(
		    "Ha ocurrido un error al crear una nueva tarea de nombre "
			    + "[%s]. Pertenece a una categoria del sistema: [%s]",
		    nombreTarea, categoriaSistema);

	    return "FRACASO";
	}
    }

    // ===========================
    // Creación de la tarea
    // ===========================

    private String crearTareaSistema(HttpServletRequest request,
	    String nombreTarea) throws BusinessException {

	String nombreCategoria = validarNombre(request
		.getParameter("nombreCategoria"));

	if (nombreCategoria == null) {
	    Log.debug("No se ha indicado el nombre de la categoria de "
		    + "la tarea que hay que crear.");

	    request.setAttribute("advertencia_usuario",
		    "Alguno de los parámetros indicados no es válido.");

	    return "FRACASO";
	}

	TaskService taskServ = Services.getTaskService();

	if (nombreCategoria.equals("Hoy")) {
	    taskServ.createTask(nombreTarea, true, ((User) request.getSession()
		    .getAttribute("user")).getId(), null);
	}

	else {
	    taskServ.createTask(nombreTarea, false,
		    ((User) request.getAttribute("user")).getId(), null);
	}

	request.setAttribute("exito_usuario", "Se ha creado una nueva "
		+ "tarea con nombre '" + nombreTarea + "'");

	return "EXITO";
    }

    private String crearTareaUsuario(HttpServletRequest request,
	    String nombreTarea) throws BusinessException {

	Long idCateg = validarIdCategoria(request);

	if (idCateg == null) {
	    Log.debug("No se ha indicado el identificador de la "
		    + "categoria de la tarea que hay que crear");

	    request.setAttribute("advertencia_usuario",
		    "Alguno de los parámetros indicados no es válido.");

	    return "FRACASO";
	}

	TaskService taskServ = Services.getTaskService();

	taskServ.createTask(nombreTarea, false, ((User) request.getSession()
		.getAttribute("user")).getId(), idCateg);

	request.setAttribute("exito_usuario", "Se ha creado una nueva "
		+ "tarea con nombre '" + nombreTarea + "'");

	return "EXITO";
    }

    // ===============================
    // Validaciones de parámetros
    // ===============================

    private String validarCategoria(HttpServletRequest request) {
	String categoria = request.getParameter("CategoriaSistema");

	if (!"NO".equals(categoria) && !"SI".equals(categoria)) {
	    return null;
	}

	return categoria;
    }

    private String validarNombre(String nombre) {
	if (nombre == null || nombre.equals("")) {
	    return null;
	}

	return nombre;
    }

    private Long validarIdCategoria(HttpServletRequest request) {
	String idCategoria = request.getParameter("idCategoria");

	if (idCategoria == null) {
	    return null;
	}

	return LongUtil.parseLong(idCategoria);
    }

}
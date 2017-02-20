package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.Filter;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.acciones.util.OrdenationInbox_And_User;
import uo.sdi.acciones.util.OrdenationToday;
import uo.sdi.acciones.util.OrdenationWeek;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ListarTareasAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Log.debug("He llegado a la accion de listar tareas");

	String categoriaSistema = request.getParameter("CategoriaSistema");

	if (categoriaSistema == null) {
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido listar correctamente sus tareas");

	    Log.error("La categoria del sistema es nula.");

	    return "FRACASO";
	}

	try {
	    if (categoriaSistema.equals("NO")) {
		Log.debug("Se van a listar tareas de una categoria de usuario");
		return listarTareasUsuario(request);
	    }

	    else {
		Log.debug("Se van a listar tareas de una categoria del sistema");
		return listarTareasSistema(request);
	    }
	}

	catch (BusinessException b) {
	    request.setAttribute("advertencia_usuario", b.getMessage());
	    Log.debug("Algo ha ocurrido obteniendo lista de tareas: %s",
		    b.getMessage());

	    return "FRACASO";
	}

    }

    private String listarTareasUsuario(HttpServletRequest request)
	    throws BusinessException {

	String id = request.getParameter("idCategoria");

	if (id == null) {
	    request.setAttribute("advertencia_usuario", "No se ha indicado el "
		    + "id de la categoria de la que se quieren ver las tareas");

	    return "FRACASO";
	}

	Long idLong = LongUtil.parseLong(id);

	if (idLong == null) {
	    request.setAttribute("advertencia_usuario", "No se ha indicado el "
		    + "id de la categoria de la que se quieren ver las tareas");

	    return "FRACASO";
	}

	TaskService taskService = Services.getTaskService();

	List<Task> listaTareas = taskService.findTasksByCategoryId(idLong);

	if (filtrarTareas(request)) {
	    Filter.removeFinishedTasks(listaTareas);
	    OrdenationInbox_And_User.sortWhitoutFinishedTasks(listaTareas);
	}

	else {
	    OrdenationInbox_And_User.sortWhitFinishedTasks(listaTareas);
	}

	request.setAttribute("listaTareas", listaTareas);

	Log.debug("Obtenida lista de tareas de la categoria '%d' "
		+ "conteniendo [%d] categorías", idLong, listaTareas.size());

	request.setAttribute("CategoriaSistema",
		request.getParameter("CategoriaSistema"));
	request.setAttribute("idCategoria", idLong);

	return "EXITO";
    }

    private String listarTareasSistema(HttpServletRequest request)
	    throws BusinessException {

	TaskService taskService = Services.getTaskService();
	User user = (User) request.getSession().getAttribute("user");

	List<Task> listaTareas;

	if (request.getParameter("nombreCategoria").equals("Inbox")) {
	    listaTareas = taskService.findInboxTasksByUserId(user.getId());

	    if (filtrarTareas(request)) {
		Filter.removeFinishedTasks(listaTareas);
		OrdenationInbox_And_User.sortWhitoutFinishedTasks(listaTareas);
	    }

	    else {
		OrdenationInbox_And_User.sortWhitFinishedTasks(listaTareas);
	    }
	}

	else if (request.getParameter("nombreCategoria").equals("Hoy")) {
	    listaTareas = taskService.findTodayTasksByUserId(user.getId());
	    OrdenationToday.sort(listaTareas);
	}

	else { // categoria Semana
	    listaTareas = taskService.findWeekTasksByUserId(user.getId());
	    OrdenationWeek.sort(listaTareas);
	}

	request.setAttribute("listaTareas", listaTareas);
	Log.debug("Obtenida lista de tareas de categorias del sistema "
		+ "conteniendo [%d] categorías", listaTareas.size());

	request.setAttribute("CategoriaSistema",
		request.getParameter("CategoriaSistema"));
	request.setAttribute("nombreCategoria",
		request.getParameter("nombreCategoria"));

	return "EXITO";
    }

    /**
     * Indica si hay activar o desactivar el filtro que quita del listado las
     * tareas finalizadas.
     * 
     */
    private boolean filtrarTareas(HttpServletRequest request) {
	String filtrar = request.getParameter("filtrarTareas");

	if (filtrar != null && filtrar.equals("NO")) {
	    return false;
	}

	return true;
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
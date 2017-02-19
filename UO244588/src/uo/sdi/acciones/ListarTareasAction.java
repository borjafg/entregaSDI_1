package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.task.command.util.OrdenationBy;
import uo.sdi.model.Task;
import uo.sdi.model.User;

public class ListarTareasAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Log.debug("He llegado a la accion de listar tareas");

	List<Task> listaTareas;
	String categoriaSistema = request.getParameter("CategoriaSistema");
	if (categoriaSistema == null) {
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido listar correctamente sus tareas");
	    Log.error("La cateogria del sistema es nula.");
	    return "FRACASO";
	}
	try {
	    if (categoriaSistema.equals("NO")) {

		TaskService taskService = Services.getTaskService();

		String id = request.getParameter("idCategoria");

		Long idLong = LongUtil.parseLong(id);

		listaTareas = taskService
			.findTasksByCategoryIdOrderByCreationDate(idLong);

		request.setAttribute("listaTareas",
			OrdenationBy.orderByCreationDate(listaTareas));

		Log.debug("Obtenida lista de tareas de la categoria '%d' "
			+ "conteniendo [%d] categorías", idLong,
			listaTareas.size());
		request.setAttribute("CategoriaSistema", request.getParameter("CategoriaSistema"));
		request.setAttribute("idCategoria", idLong);
		return "EXITO";

	    } else {
		TaskService taskService = Services.getTaskService();
		User user = (User) request.getSession().getAttribute("user");
		if (request.getParameter("nombreCategoria").equals("Inbox")) {
		    listaTareas = OrdenationBy.orderByPlannedDate(taskService
			    .findInboxTasksByUserId(user.getId()));

		} else if (request.getParameter("nombreCategoria")
			.equals("Hoy")) {
		    listaTareas = OrdenationBy.orderByPlannedDate(taskService
			    .findTodayTasksByUserId(user.getId()));

		} else {// categoriaa Semana, ordenacion no es la adecuada
		    listaTareas = OrdenationBy.orderByPlannedDate(taskService
			    .findWeekTasksByUserId(user.getId()));

		}

		request.setAttribute("listaTareas", listaTareas);
		Log.debug("Obtenida lista de tareas de categorias del sistema "
			+ "conteniendo [%d] categorías", listaTareas.size());
		
		request.setAttribute("CategoriaSistema", request.getParameter("CategoriaSistema"));
		request.setAttribute("CategoriaSistema", request.getParameter(request.getParameter("nombreCategoria")));
		return "EXITO";
	    }

	} catch (BusinessException b) {
	    request.setAttribute("advertencia_usuario", b.getMessage());
	    Log.debug("Algo ha ocurrido obteniendo lista de tareas: %s",
		    b.getMessage());
	    return "FRACASO";
	}

    }
    
    

    @Override
    public String toString() {
	return getClass().getName();
    }
}

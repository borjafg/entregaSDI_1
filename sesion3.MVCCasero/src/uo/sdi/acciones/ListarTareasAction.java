package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
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

	String resultado = "EXITO";
	List<Task> listaTareas;

	try {
	    if (request.getAttribute("CategoriaSistema").equals("NO")) {

		TaskService taskService = Services.getTaskService();

		listaTareas = taskService
			.findTasksByCategoryIdOrderByCreationDate((Long) request
				.getAttribute("Id"));

		request.setAttribute("listaTareas",
			OrdenationBy.orderByCreationDate(listaTareas));

		Log.debug("Obtenida lista de tareas de la categoria '%l' "
			+ "conteniendo [%d] categorías",
			(Long) request.getAttribute("Id"), listaTareas.size());

	    } else if (request.getAttribute("CategoriaSistema").equals("SI")) {
		TaskService taskService = Services.getTaskService();
		User user = (User) request.getAttribute("user");
		if (request.getAttribute("nombreCategoria").equals("Inbox")) {
		    listaTareas =  OrdenationBy.orderByProvidedDate(taskService.findInboxTasksByUserId(user
			    .getId()));

		} else if (request.getAttribute("nombreCategoria")
			.equals("Hoy")) {
		    listaTareas = OrdenationBy.orderByProvidedDate(taskService
			    .findTodayTasksByUserId(user.getId()));

		} else {// categoriaa Semana, ordenacion no es la adecuada
		    listaTareas =  OrdenationBy.orderByProvidedDate(taskService.findWeekTasksByUserId(user
			    .getId()));

		}

		request.setAttribute("listaTareas", listaTareas);

	    }

	} catch (BusinessException b) {
	    Log.debug("Algo ha ocurrido obteniendo lista de tareas: %s",
		    b.getMessage());
	    resultado = "FRACASO";
	}

	return resultado;
    }

    @Override
    public String toString() {
	return getClass().getName();
    }
}

package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;

public class ModificarTareaAction extends AbstractModificarDatosTareaAction {

    @Override
    protected String procesarPeticion(HttpServletRequest request,
	    HttpServletResponse response) {

	String identTarea = request.getParameter("idTarea");

	if (identTarea != null) {
	    Long idTarea = LongUtil.parseLong(identTarea);

	    if (idTarea == null) {
		request.setAttribute("advertencia_usuario", "Falta indicar de "
			+ "que tarea se quieren cambiar los datos");

		return "FRACASO";
	    }

	    try {
		TaskService taskServ = Services.getTaskService();

		Task tarea = taskServ.findTaskById(idTarea);

		List<Category> categories = taskServ
			.findCategoriesByUserId(((User) request.getSession()
				.getAttribute("user")).getId());

		request.setAttribute("tarea", tarea);
		request.setAttribute("categoriasUsuario", categories);

		return "EXITO";
	    }

	    catch (BusinessException be) {
		Log.debug("No se ha podido pasar al formulario de "
			+ "modificacion de los datos de una tarea. Causa"
			+ "del error: %s", be.getMessage());

		request.setAttribute("advertencia_usuario", be.getMessage());
		return "FRACASO";
	    }
	}

	request.setAttribute("advertencia_usuario",
		"Falta indicar de qué tarea se quieren modificar los datos");

	return "FRACASO";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
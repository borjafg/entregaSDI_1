package uo.sdi.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import alb.util.log.Log;

public class ModificarDatosTareaAction extends
	AbstractModificarDatosTareaAction {

    @Override
    protected String procesarPeticion(HttpServletRequest request,
	    HttpServletResponse response) {

	String identTarea = request.getParameter("idTarea");

	if (identTarea != null) {
	    Long idTarea = LongUtil.parseLong(identTarea);

	    if (idTarea == null) {
		request.setAttribute("advertencia_usuario", "Falta indicar de "
			+ "qué tarea se quieren cambiar los datos");

		return "FRACASO";
	    }

	    String nombreTarea = request.getParameter("nombre");
	    String comentario = request.getParameter("comentario");
	    Date fechaPlaneada = validarFecha(request
		    .getParameter("fechaPlaneada"));
	    Long idCategoria = validarLong(request.getParameter("idCategTarea"));

	    if (nombreTarea == null || comentario == null
		    || fechaPlaneada == null || idCategoria == null) {

		request.setAttribute("advertencia_usuario", "Falta indicar de "
			+ "qué tarea se quieren cambiar los datos");

		return "FRACASO";
	    }

	    try {
		TaskService taskServ = Services.getTaskService();

		taskServ.updateTask(idTarea, nombreTarea, comentario,
			fechaPlaneada, idCategoria);

		request.setAttribute("exito_usuario", "Se han modificado con "
			+ "éxito los datos de la tarea");

		return "EXITO";
	    }

	    catch (BusinessException be) {
		Log.debug("No se ha podido modificar los datos de la "
			+ "tarea. Causa del error: %s", be.getMessage());

		request.setAttribute("advertencia_usuario", be.getMessage());
		return "FRACASO";
	    }
	}

	request.setAttribute("advertencia_usuario",
		"Falta indicar de qué tarea se quieren modificar los datos");

	return "FRACASO";
    }

    private Long validarLong(String valor) {
	if (valor == null) {
	    return null;
	}

	return LongUtil.parseLong(valor);
    }

    private Date validarFecha(String fecha) {
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	    return sdf.parse(fecha);
	}

	catch (ParseException excep) {
	    return null;
	}
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;

public class FinalizarTareaAction implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	String identificador = (String) request.getParameter("id");
	
	if(identificador == null){
	    Log.error("El identificador de la tarea a sido null sacarlo por request.getParameter('id')");
	    request.setAttribute("advertencia_usuario", "No se ha podido finalizar la tarea");
	    return "FRACASO";
	}
	Long id = LongUtil.parseLong(identificador);
	if(id == null){
	    Log.error("El identificador de la tarea a sido null al transformarlo a Long");
	    request.setAttribute("advertencia_usuario", "No se ha podido finalizar la tarea");
	    return "FRACASO";
	}
	try{
	    TaskService tskServ = Services.getTaskService();
	    tskServ.markTaskAsFinished(id);
	    request.setAttribute("exito_usuario",
		    "Se ha finalizado la tarea");
	    Log.debug("Se ha finalizado con exito la tarea con id [%d] ",id);

	    return "EXITO";
	}catch(BusinessException b){
	    Log.error("No se ha podido finalizar la tarea con id [%d]. Causa del error: %s",id,b.getMessage());
	    request.setAttribute("advertencia_usuario", b.getMessage());
	    return "FRACASO";
	}
    }

}

package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;
import alb.util.log.Log;

public class NuevaCategoriaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	User user =(User) request.getSession().getAttribute("user");
	if(user == null){
	    Log.error("El usuario es nulo");
	    request.setAttribute("advertencia_usuario", "No se ha podido crear la categoria");
	    return "FRACASO";
	}
	TaskService tskSer = Services.getTaskService();
	String nombreCategoria = (String) request
		.getParameter("nombreCategoriaNueva");
	if(nombreCategoria == null){	    
	    Log.error("El nombre de la categoría es nulo");
	    request.setAttribute("advertencia_usuario", "No se ha podido crear la categoria");
	    return "FRACASO";
	}
	
	try {
	    tskSer.createCategory(nombreCategoria, user.getId());
	    request.setAttribute("exito_usuario","Se ha creado la categoria con exito");
	    return "EXITO";
	} catch (BusinessException e) {
	    Log.debug("Error:Al ha ocurrido mientras se creaba una categoría %s",
		    e.getMessage());
	    request.setAttribute("advertencia_usuario", "No se ha podido "
	    	+ "crear la categoria");
	    return "FRACASO";
	}
    }

}

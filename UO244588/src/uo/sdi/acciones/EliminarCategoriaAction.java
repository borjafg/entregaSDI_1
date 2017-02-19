package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;
import alb.util.log.Log;

public class EliminarCategoriaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.debug("He llegado a la accion de eliminar categorias");
	
	
	TaskService tsk = Services.getTaskService();
	
	User user = (User)request.getSession().getAttribute("user");
	if(user == null){
	    Log.error("El usuario es null");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido eliminar correctamente la categoria");
	    return "FRACASO";  
	}
	
	String identificador = request.getParameter("idCategoria");
	if(identificador == null){
	    Log.error("El identificador de la categoria es null (request -> String)");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido eliminar correctamente la categoria");
	    return "FRACASO";  
	}
	Long id = LongUtil.parseLong(identificador);
	if(id == null){
	    Log.error("El identificador de la categoria es null (String -> Long)");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido elimnar correctamente la categoria");
	    return "FRACASO";
	}
	
	try{
	    tsk.deleteCategory(id,user.getId());
	    request.setAttribute("exito_usuario",
		    "Se ha eliminado la categoria correctamente");
	    return "EXITO";
	}catch(BusinessException e){
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido eliminar correctamente la categoria");
	   Log.debug("Algo ha ocurrido eliminando categorias: %s",e.getMessage());
	   return "FRACASO";
	}
    }

}

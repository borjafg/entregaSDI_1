package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;

public class DuplicarCategoriaAction implements Accion{

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	
	Log.debug("He llegado a la accion de duplicar categorias");
	TaskService tsk = Services.getTaskService();
	
	String identificador = request.getParameter("idCategoria");
	if(identificador == null){
	    Log.error("El identificador de la categoria es null (request -> String)");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido duplicar correctamente la categoria");
	    return "FRACASO";
	    
	}
	Long id = LongUtil.parseLong(identificador);
	if(id == null){
	    Log.error("El identificador de la categoria es null (String -> Long)");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido duplicar correctamente la categoria");
	    return "FRACASO";
	}
	
	try{
	    tsk.duplicateCategory(id);
	    request.setAttribute("exito_usuario",
		    "Se ha duplicado la categoria correctamente");
	    return "EXITO";
	}catch(BusinessException e){
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido duplicar correctamente la categoria");
	   Log.debug("Algo ha ocurrido duplicando las categorias %s",e.getMessage());
	   return "FRACASO";
	}

    }

}

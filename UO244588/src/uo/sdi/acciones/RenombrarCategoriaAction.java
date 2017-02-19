package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import alb.util.log.Log;

public class RenombrarCategoriaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.debug("He llegado a la accion de renombrar categorias");
	TaskService tsk = Services.getTaskService();
	
	
	String nombreNuevo = request.getParameter("nombreCategoria"); 
	if(nombreNuevo== null){
	    Log.error("El nombre es null");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido renombrar correctamente la categoria");
	    return "FRACASO"; 
	}
	
	String identificador = request.getParameter("idCategoria");
	if(identificador == null){
	    Log.error("El identificador de la categoria es null (request -> String)");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido renombrar correctamente la categoria");
	    return "FRACASO";  
	}
	Long id = LongUtil.parseLong(identificador);
	if(id == null){
	    Log.error("El identificador de la categoria es null (String -> Long)");
	    request.setAttribute("advertencia_usuario",
		    "no se ha podidorenombrar correctamente la categoria");
	    return "FRACASO";
	}
	
	try{
	    tsk.updateCategory(id, nombreNuevo);
	    request.setAttribute("exito_usuario",
		    "Se ha renombrado la categoria correctamente");
	    return "EXITO";
	}catch(BusinessException e){
	    request.setAttribute("advertencia_usuario",
		    "no se ha podido renombrar correctamente la categoria");
	   Log.debug("Algo ha ocurrido r categorias: %s",e.getMessage());
	   return "FRACASO";
	    
	}
	
	
    }

}

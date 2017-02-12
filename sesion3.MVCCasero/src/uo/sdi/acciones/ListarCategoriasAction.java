package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.business.Services;
import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Category;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ListarCategoriasAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String resultado = "EXITO";

	List<Category> listaCategorias;

	try {
	    User user = (User) request.getAttribute("user");

	    TaskService taskService = Services.getTaskService();

	    listaCategorias = taskService.findCategoriesByUserId(user.getId());
	    request.setAttribute("listaCategorias", listaCategorias);

	    Log.debug("Obtenida lista de categorías del usuario '%s' "
		    + "conteniendo [%d] categorías", user.getLogin(),
		    listaCategorias.size());
	}

	catch (BusinessException b) {
	    Log.debug("Algo ha ocurrido obteniendo lista de categorías: %s",
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
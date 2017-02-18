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

	List<Category> listaCategorias;

	try {
	    User user = (User) request.getAttribute("user");

	    TaskService taskService = Services.getTaskService();

	    listaCategorias = taskService.findCategoriesByUserId(user.getId());
	    request.setAttribute("listaCategorias", listaCategorias);

	    Log.debug("Obtenida lista de categorías del usuario '%s' "
		    + "conteniendo [%d] categorías", user.getLogin(),
		    listaCategorias.size());

	    return "EXITO";

	}

	catch (BusinessException b) {
	    request.getSession().invalidate();
	    Log.debug("Algo ha ocurrido obteniendo lista de categorías: %s",
		    b.getMessage());
	    request.setAttribute("advertencia_usuario", "No se ha podido "
	    	+ "listar las categorias del usuario");
	    return "FRACASO";

	}

    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
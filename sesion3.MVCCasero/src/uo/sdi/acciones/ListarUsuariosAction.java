package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.business.AdminService;
import uo.sdi.business.Services;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;
import alb.util.log.Log;

public class ListarUsuariosAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	try {
	    AdminService adminServ = Services.getAdminService();
	    List<User> listaUsuarios = adminServ.findAllUsers();

	    // El admin no debería poder modificar su cuenta
	    listaUsuarios.remove((User) request.getSession().getAttribute(
		    "user"));

	    Log.debug("Se ha obtenido la lista de usuarios del sistema, "
		    + "conteniendo [%d] usuarios (sin tener en cuenta al "
		    + "usuario actual)", listaUsuarios.size());

	    request.setAttribute("listaUsuarios", listaUsuarios);

	    return "EXITO";
	}

	// Nunca salta una excepcion de este tipo, pero el compilador
	// obliga a poner el try/catch
	//
	catch (BusinessException bs) {
	    request.getSession().invalidate();

	    Log.error("Ha ocurrido un error al listar los usuarios del "
		    + "sistema. Causa del error: [%s]", bs.getMessage());

	    return "FRACASO";
	}
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
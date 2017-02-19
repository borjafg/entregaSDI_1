package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.AdminService;
import uo.sdi.business.Services;
import uo.sdi.business.exception.BusinessException;

public class BorrarUsuarioAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Long userId = validarParametros(request);

	if (userId == null) {
	    request.setAttribute("advertencia_usuario",
		    "No se ha podido borrar el usuario");

	    return "FRACASO";
	}

	try {
	    AdminService admServ = Services.getAdminService();

	    admServ.deepDeleteUser(userId);

	    request.setAttribute("exito_usuario",
		    "Se ha borrado al usuario del sistema");

	    return "EXITO";
	}

	catch (BusinessException be) {
	    Log.error("No se ha podido eliminar al usuario con id [%d] del "
		    + "sistema. Causa del error: ", userId, be.getMessage());

	    request.setAttribute("advertencia_usuario", be.getMessage());

	    return "FRACASO";
	}
    }

    /**
     * Valida los parámetros de la petición del usuario, y pasa a formato Long
     * el id del usuario que hay que borrar del sistema.
     * 
     * @param request
     *            petición realizada por el usuario
     * 
     * @return identificador del usuario convertido en Long, o null si los
     *         parámetros no son válidos.
     * 
     */
    private Long validarParametros(HttpServletRequest request) {
	String id = (String) request.getParameter("id");

	if (id == null) {
	    return null;
	}

	return LongUtil.parseLong(id);
    }

}
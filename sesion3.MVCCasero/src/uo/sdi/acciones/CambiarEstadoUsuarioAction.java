package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.acciones.util.LongUtil;
import uo.sdi.business.AdminService;
import uo.sdi.business.Services;
import uo.sdi.business.exception.BusinessException;

public class CambiarEstadoUsuarioAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	String opcion = (String) request.getParameter("opcion");
	String id = (String) request.getParameter("id");
	Long idUser = validarParametros(opcion, id);

	if (idUser == null) {
	    Log.debug("No se ha podido borrar un usuario porque faltaba algún "
		    + "parámetro en la petición");

	    request.setAttribute("advertencia_usuario",
		    "No se ha podido cambiar el estado del usuario");

	    return "FRACASO";
	}

	else {
	    try {
		AdminService admServ = Services.getAdminService();
		String resultado;

		if (opcion.equals("habilitar")) {
		    admServ.enableUser(idUser);

		    Log.debug("Se ha habilitado la cuenta del usuario con id "
			    + "[%d]", idUser);

		    resultado = "Se ha habilitado el usuario";
		}

		else {
		    admServ.disableUser(idUser);

		    Log.debug("Se ha deshabilitado la cuenta del usuario con "
			    + "id [%d]", idUser);

		    resultado = "Se ha deshabilitado el estado del usuario";
		}

		request.setAttribute("exito_usuario", resultado);

		return "EXITO";
	    }

	    catch (BusinessException be) {
		request.setAttribute("advertencia_usuario", be.getMessage());

		return "FRACASO";
	    }
	}
    }

    /**
     * Valida los parámetros de la petición del usuario, y pasa a formato Long
     * el id del usuario del que hay que cambiar el estado.
     * 
     * @param opcion
     *            "habilitar" o "deshabilitar" la cuenta del usuario
     * 
     * @param id
     *            identificador del usuario del que hay que cambiar el estado de
     *            la cuenta
     * 
     * @return identificador del usuario convertido en Long, o null si los
     *         parámetros no son válidos.
     * 
     */
    private Long validarParametros(String opcion, String id) {
	if ((!"deshabilitar".equals(opcion) && !"habilitar".equals(opcion))
		|| id == null) {

	    return null;
	}

	return LongUtil.parseLong(id);
    }

}
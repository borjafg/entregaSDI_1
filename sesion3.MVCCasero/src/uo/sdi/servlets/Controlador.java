package uo.sdi.servlets;

import java.io.IOException;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.Accion;
import uo.sdi.model.User;
import uo.sdi.servlets.util.InicializadorMapas;
import uo.sdi.servlets.util.InicializadorMapasJAVA;
import alb.util.log.Log;

public class Controlador extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;

    // Mapa<"rol", Mapa< "opcion", "objeto Accion" >>
    //
    private Map<String, Map<String, Accion>> mapaDeAcciones;

    // Mapa<"rol", Mapa< "opcion", Mapa<"resultado", "JSP"> >>
    //
    private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion;

    /**
     * Se ejecuta cuando se crea el servlet. Inicializa el mapa de acciones y el
     * mapa de navegación.
     * 
     */
    public void init() throws ServletException {
	InicializadorMapas init = new InicializadorMapasJAVA();

	mapaDeAcciones = init.crearMapaAcciones();
	mapaDeNavegacion = init.crearMapaDeNavegacion();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException {

	doGet(req, res);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {

	String accionNavegadorUsuario, resultado, jspSiguiente;
	Accion objetoAccion;
	String rolAntes, rolDespues;

	try {
	    // Obtener el string que hay a la derecha que hay después de
	    // la URL del dominio /
	    accionNavegadorUsuario = request.getServletPath().replace("/", "");

	    rolAntes = obtenerRolDeSesion(request);

	    objetoAccion = buscarObjetoAccionParaAccionNavegador(rolAntes,
		    accionNavegadorUsuario);

	    request.removeAttribute("mensajeParaElUsuario");

	    resultado = objetoAccion.execute(request, response);

	    // Puede ser que el rol del usuario cambie
	    // despues de ejecutar la accion

	    rolDespues = obtenerRolDeSesion(request);

	    jspSiguiente = buscarJSPEnMapaNavegacionSegun(rolDespues,
		    accionNavegadorUsuario, resultado);

	    request.setAttribute("jspSiguiente", jspSiguiente);
	}

	catch (PersistenceException e) {
	    request.getSession().invalidate();

	    Log.error("Se ha producido alguna excepción relacionada con "
		    + "la persistencia [%s]", e.getMessage());

	    request.setAttribute("mensajeParaElUsuario",
		    "Error irrecuperable: contacte con el responsable "
			    + "de la aplicación");

	    jspSiguiente = "/login.jsp";
	}

	catch (Exception e) {
	    request.getSession().invalidate();

	    Log.error("Se ha producido alguna excepción no manejada [%s]",
		    e.getMessage());

	    request.setAttribute("mensajeParaElUsuario",
		    "Error irrecuperable: contacte con el responsable de "
			    + "la aplicación");

	    jspSiguiente = "/login.jsp";
	}

	RequestDispatcher dispatcher = getServletContext()
		.getRequestDispatcher(jspSiguiente);

	dispatcher.forward(request, response);
    }

    /**
     * Comprueba el rol del usuario que ha realizado la peticion.
     * 
     * @param req
     *            petición del usuario
     * 
     * @return rol del usuario
     * 
     */
    private String obtenerRolDeSesion(HttpServletRequest req) {
	HttpSession sesion = req.getSession();

	if (sesion.getAttribute("user") == null)
	    return "ANONIMO";

	else if (((User) sesion.getAttribute("user")).getIsAdmin())
	    return "ADMIN";

	else
	    return "USUARIO";
    }

    /**
     * Obtiene un objeto accion en función de la opción enviada desde el
     * navegador
     * 
     * @param rol
     *            rol del usuario que realiza la peticion
     * @param opcion
     *            accion enviada desde el navegador del usuario
     * 
     * @return accion que se debe ejecutar para llevar a cabo la petición.
     * 
     */
    private Accion buscarObjetoAccionParaAccionNavegador(String rol,
	    String opcion) {

	Accion accion = mapaDeAcciones.get(rol).get(opcion);

	Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]", accion,
		opcion, rol);

	return accion;
    }

    /**
     * Obtiene la página JSP a la que habrá que entregar el control. La
     * selección se realizará en función de la opción enviada desde el navegador
     * y el resultado de la ejecución de la acción asociada.
     * 
     * @param rol
     *            rol del usuario que realiza la petición
     * @param opcion
     *            accion enviada desde el navegador del usuario
     * @param resultado
     *            resultado de ejecutar la accion correspondiente a esa opcion
     * 
     * @return pagina jsp que debe servirse a al usuario
     * 
     */
    private String buscarJSPEnMapaNavegacionSegun(String rol, String opcion,
	    String resultado) {

	String jspSiguiente = mapaDeNavegacion.get(rol).get(opcion)
		.get(resultado);

	Log.debug("Elegida página siguiente [%s] para el resultado [%s] tras "
		+ "realizar [%s] con rol [%s]", jspSiguiente, resultado,
		opcion, rol);

	return jspSiguiente;
    }

}
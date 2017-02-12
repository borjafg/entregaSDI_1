package uo.sdi.servlets.util;

import java.util.HashMap;
import java.util.Map;

import uo.sdi.acciones.Accion;
import uo.sdi.acciones.ModificarDatosAction;
import uo.sdi.acciones.ValidarseAction;

/**
 * Clase auxiliar que inicializa los mapas de opciones de de navegación que
 * utiliza el controlador para
 * 
 */
public class InicializadorMapas {

    /**
     * Inicializa el mapa que asocia una acción a cada posible opción de un rol
     * usuario.<br/>
     * <br/>
     * Rol del usuario --> opción --> acción
     * 
     */
    public Map<String, Map<String, Accion>> crearMapaAcciones() {
	Map<String, Map<String, Accion>> mapaDeAcciones = new HashMap<String, Map<String, Accion>>();

	// =================================
	// === Mapa acciones --> ANONIMO ===
	// =================================

	Map<String, Accion> mapaPublico = new HashMap<String, Accion>();
	mapaPublico.put("validarse", new ValidarseAction());

	mapaDeAcciones.put("ANONIMO", mapaPublico); // Rol de usuario

	// =================================
	// === Mapa acciones --> USUARIO ===
	// =================================

	Map<String, Accion> mapaRegistrado = new HashMap<String, Accion>();
	mapaRegistrado.put("modificarDatos", new ModificarDatosAction());

	mapaDeAcciones.put("USUARIO", mapaRegistrado); // Rol de usuario

	// // ===============================
	// // === Mapa acciones --> ADMIN ===
	// // ===============================
	//
	// Map<String, Accion> mapaAdministrador = new HashMap<String,
	// Accion>();
	// mapaAdministrador.put("modificarDatos", new ModificarDatosAction());
	//
	// mapaDeAcciones.put("ADMIN", mapaAdministrador); // Rol de usuario

	return mapaDeAcciones;
    }

    /**
     * Inicializa el mapa que asocia una pagina JSP a cada posible resultado de
     * ejecutar una accion asociada a un opcion de un rol de usuario.<br/>
     * <br/>
     * Rol del usuario --> opcion --> resultado --> pagina JSP
     * 
     */
    public Map<String, Map<String, Map<String, String>>> crearMapaDeNavegacion() {
	Map<String, Map<String, Map<String, String>>> mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

	Map<String, Map<String, String>> opcionResultadoYJSP = null;
	Map<String, String> resultadoYJSP = null;

	// ===================================
	// === Mapa navegación --> ANONIMO ===
	// ===================================

	// Inicializar mapas
	opcionResultadoYJSP = new HashMap<String, Map<String, String>>();
	resultadoYJSP = new HashMap<String, String>();

	// (1) inicializar mapa de resultados (para cada opcion)
	// (2) guardar posibles resultados
	resultadoYJSP.put("FRACASO", "/login.jsp"); // resultado

	// (3) guardar opcion
	opcionResultadoYJSP.put("validarse", resultadoYJSP); // opcion

	// (4) despues de crear y guardar las opciones, guardarlas en su rol
	mapaDeNavegacion.put("ANONIMO", opcionResultadoYJSP); // rol

	// ===================================
	// === Mapa navegación --> USUARIO ===
	// ===================================

	// Inicializar mapas
	opcionResultadoYJSP = new HashMap<String, Map<String, String>>();
	resultadoYJSP = new HashMap<String, String>();

	resultadoYJSP.put("EXITO", "/principalUsuario.jsp"); // resultado
	opcionResultadoYJSP.put("validarse", resultadoYJSP); // opcion

	resultadoYJSP = new HashMap<String, String>();
	resultadoYJSP.put("EXITO", "/principalUsuario.jsp"); // resultado
	resultadoYJSP.put("FRACASO", "/principalUsuario.jsp"); // resultado
	opcionResultadoYJSP.put("modificarDatos", resultadoYJSP); // opcion

	mapaDeNavegacion.put("USUARIO", opcionResultadoYJSP); // rol

	// =================================
	// === Mapa navegación --> ADMIN ===
	// =================================

	// // Inicializar mapas
	// opcionResultadoYJSP = new HashMap<String, Map<String, String>>();
	// resultadoYJSP = new HashMap<String, String>();

	return mapaDeNavegacion;
    }
}
package uo.sdi.servlets.util;

import java.util.Map;

import uo.sdi.acciones.Accion;

/**
 * Clase auxiliar que inicializa el mapa de opciones y el mapa de navegación que
 * utiliza el controlador para decidir como procesar las peticiones de los
 * usuarios.
 * 
 */
public interface InicializadorMapas {

    /**
     * Inicializa el mapa que asocia una <i>acción</i> a cada posible
     * <i>opción</i> de un <i>rol usuario</i>.<br/>
     * <br/>
     * Rol del usuario --> opción --> acción
     * 
     */
    public Map<String, Map<String, Accion>> crearMapaAcciones();

    /**
     * Inicializa el mapa que asocia una <i>página JSP</i> a cada posible
     * <i>resultado</i> de ejecutar una accion asociada a una <i>opción</i> de
     * un <i>rol de usuario</i>.<br/>
     * <br/>
     * Rol del usuario --> opción --> resultado --> página JSP
     * 
     */
    public Map<String, Map<String, Map<String, String>>> crearMapaDeNavegacion();

}
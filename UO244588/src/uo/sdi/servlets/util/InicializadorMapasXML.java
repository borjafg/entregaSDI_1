package uo.sdi.servlets.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import uo.sdi.acciones.Accion;
import uo.sdi.servlets.exception.InitializationException;

public class InicializadorMapasXML {

    private SAXBuilder builder;

    public InicializadorMapasXML() {
	builder = new SAXBuilder();
    }

    // =================================================
    // Métodos auxiliares usados para crear los mapas
    // =================================================

    /**
     * Devuelve todos los nodos, con la etiqueta rol, que están contenidos
     * dentro del nodo raíz de un fichero cuyo nombre se indica como parámetro.
     * 
     * @param name
     *            nombre del fichero que hay que examinar
     * 
     * @return lista de nodos que están un nivel por debajo del nodo raíz y que
     *         tienen la etiqueta [rol]
     * 
     * @throws IOException
     *             Ha ocurrido un error al leer el contenido del fichero
     * 
     * @throws JDOMException
     *             Ha ocurrido un error al analizar el contenido del fichero
     * 
     * 
     */
    private List<Element> getRolElements_File(String name)
	    throws JDOMException, IOException {

	URL ruta = this.getClass().getClassLoader().getResource(name);
	File xmlFile = new File(ruta.getPath());

	Document document = builder.build(xmlFile);

	return document.getRootElement().getChildren("rol");
    }

    /**
     * Crea una instancia de la clase que se le pasa como parámetro. Además,
     * valida que la clase que se va a crear implementa la interfaz
     * {@link Accion} y se encuentre en el paquete {@link uo.sdi.acciones}.
     * 
     * @param className
     *            nombre de la clase que se va a instanciar
     * 
     * @return instancia de la clase que se haya indicado como parámetro.
     * 
     * @throws InitializationException
     *             Ha ocurrido un error al crear una instancia de la clase
     *             indicada (no existe o no implementa {@link Accion}).
     * 
     */
    private Accion getInstanceOfActionClass(String className)
	    throws InitializationException {

	Class<?> clase;
	Constructor<?> constructor;
	Object instancia;

	try {
	    clase = Class.forName("uo.sdi.acciones." + className);

	    try {
		constructor = clase.getConstructor();
	    }

	    catch (NoSuchMethodException nme) {
		throw new InitializationException("No se ha podido crear una "
			+ "instancia de la clase '" + className + "' usando "
			+ "su contructor sin parámetros.");
	    }

	    catch (SecurityException se) {
		throw new InitializationException("Ha ocurrido un error al "
			+ "intentar crear una instancia de la clase '"
			+ className + "'");
	    }

	    try {
		instancia = constructor.newInstance();

		if (instancia instanceof Accion) {
		    return (Accion) instancia;
		}

		throw new InitializationException("La clase '" + className
			+ "' no implementa la interfaz Accion.");
	    }

	    catch (InstantiationException | IllegalAccessException
		    | IllegalArgumentException | InvocationTargetException e) {

		throw new InitializationException("Ha ocurrido un error al "
			+ "intentar crear una instancia de la clase '"
			+ className + "'");
	    }
	}

	catch (ClassNotFoundException cnfe) {
	    throw new InitializationException("No se ha encontrado la clase '"
		    + className + "'");
	}
    }

    // ================================
    // Métodos que generan los mapas
    // ================================

    public Map<String, Map<String, Accion>> crearMapaAcciones()
	    throws InitializationException {

	Map<String, Map<String, Accion>> mapaAcciones = new HashMap<String, Map<String, Accion>>();
	Map<String, Accion> opcionesYAcciones = null;

	try {
	    // (1) Obtener lista de roles
	    List<Element> roles = getRolElements_File("mapa_acciones.xml");

	    List<Element> opciones = null;
	    Accion accion = null;

	    for (Element rol : roles) {
		opcionesYAcciones = new HashMap<String, Accion>();

		// (2) Sacar lista de opciones para ese [rol]
		opciones = rol.getChildren("opcion");

		for (Element opcion : opciones) {

		    // (3) Crear instancia de la [accion] asociada la [opcion]
		    accion = getInstanceOfActionClass(opcion
			    .getAttributeValue("valorAccion"));

		    // (4) Guardar cada [opcion] asociada a ese [rol]
		    opcionesYAcciones.put(
			    opcion.getAttributeValue("valorOpcion"), accion);
		}

		// (5) Guardar el [rol] junto con sus opciones y acciones
		mapaAcciones.put(rol.getAttributeValue("valorRol"),
			opcionesYAcciones);
	    }

	    return mapaAcciones;
	}

	catch (IOException io) {
	    throw new InitializationException("Ha ocurrido un error al leer "
		    + "el xml del mapa de navegacion");
	}

	catch (JDOMException jdomex) {
	    throw new InitializationException("Ha ocurrido un error al leer "
		    + "el contenido del xml del mapa de navegacion");
	}
    }

    public Map<String, Map<String, Map<String, String>>> crearMapaDeNavegacion()
	    throws InitializationException {

	Map<String, Map<String, Map<String, String>>> mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

	Map<String, Map<String, String>> opcionResultadoYJSP = null;
	Map<String, String> resultadoYJSP = null;

	try {
	    // (1) Sacamos todos los hijos del mapa_navegacion con tag [rol]
	    List<Element> roles = getRolElements_File("mapa_navegacion.xml");

	    // ========================================================
	    // Analizamos los hijos y rellenamos el mapa
	    //
	    // Mapa<"rol", Mapa< "opcion", Mapa<"resultado", "JSP"> >>
	    // ========================================================

	    List<Element> opciones = null;
	    List<Element> resultados = null;

	    for (Element rol : roles) {
		opcionResultadoYJSP = new HashMap<String, Map<String, String>>();

		// (2) Sacamos los hijos del "rol" que tienen el tag [opcion]
		opciones = rol.getChildren("opcion");

		for (Element opcion : opciones) {
		    resultadoYJSP = new HashMap<String, String>();

		    // (3) Sacamos los hijos de "opcion" con el tag [resultado]
		    resultados = opcion.getChildren("resultado");

		    for (Element resultado : resultados) {
			// (4) Guardar resultado y pagina JSP
			resultadoYJSP.put(
				resultado.getAttributeValue("valorResultado"),
				resultado.getAttributeValue("valorJSP"));
		    }

		    // (5) Guardamos la opcion y sus posibles resultados
		    opcionResultadoYJSP.put(
			    opcion.getAttributeValue("valorOpcion"),
			    resultadoYJSP);
		}

		// (6) Guardamos el rol y sus posibles opciones
		mapaDeNavegacion.put(rol.getAttributeValue("valorRol"),
			opcionResultadoYJSP);
	    }

	    return mapaDeNavegacion;
	}

	catch (IOException io) {
	    throw new InitializationException("Ha ocurrido un error al leer "
		    + "el xml del mapa de navegacion");
	}

	catch (JDOMException jdomex) {
	    throw new InitializationException("Ha ocurrido un error al leer "
		    + "el contenido del xml del mapa de navegacion");
	}
    }

}
package uo.sdi.servlets.util;

import java.io.File;
import java.io.IOException;
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

    public Map<String, Map<String, Accion>> crearMapaAcciones() {
	// TODO Auto-generated method stub
	return null;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Map<String, Map<String, String>>> crearMapaDeNavegacion()
	    throws InitializationException {

	SAXBuilder builder = new SAXBuilder();
	File xmlFile = new File("mapa_navegacion.xml");

	Map<String, Map<String, Map<String, String>>> mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

	Map<String, Map<String, String>> opcionResultadoYJSP = null;
	Map<String, String> resultadoYJSP = null;

	try {
	    // creamos el documento a traves del archivo file
	    Document document = builder.build(xmlFile);

	    // Obtenemos la raiz del XML
	    Element rootNode = document.getRootElement();

	    // obtenemos una lista de todos los hijos de mapa_navegacion con
	    // tag=rol
	    List<Element> list = (List<Element>) rootNode.getChild("rol");

	    // vamos recorriendo todos los hijos de mapa_navegacion
	    for (int i = 0; i < list.size(); i++) {
		opcionResultadoYJSP = new HashMap<String, Map<String, String>>();

		mapaDeNavegacion.put(list.get(0).getAttributeValue("valorRol"),
			opcionResultadoYJSP);
	    }

	}

	catch (IOException io) {
	    throw new InitializationException("Ha ocurrido un error al leer "
		    + "el xml del mapa de navegacion");
	}

	catch (JDOMException jdomex) {
	    throw new InitializationException("Ha ocurrido un error al leer "
		    + "el contenido del xml del mapa de navegacion");
	}

	return mapaDeNavegacion;
    }

}
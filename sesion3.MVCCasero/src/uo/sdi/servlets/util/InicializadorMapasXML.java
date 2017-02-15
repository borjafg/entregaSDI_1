package uo.sdi.servlets.util;

import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;




import uo.sdi.acciones.Accion;

import uo.sdi.acciones.Accion;

public class InicializadorMapasXML implements InicializadorMapas {

    public Map<String, Map<String, Accion>> crearMapaAcciones() {
	// TODO Auto-generated method stub
	return null;
    }

    public Map<String, Map<String, Map<String, String>>> crearMapaDeNavegacion() {
    	SAXBuilder builder = new SAXBuilder();
    	File xmlFile = new File ("/resources/mapa_navegacion.xml");
    	
    	
    	Map<String, Map<String, Map<String, String>>> mapaDeNavegacion = new HashMap<String, Map<String, Map<String, String>>>();

    	Map<String, Map<String, String>> opcionResultadoYJSP = null;
    	Map<String, String> resultadoYJSP = null;
    	
    	try{
    		//creamos el documento a traves del archivo file
    		Document document =  builder.build(xmlFile);
    		
    		//Obtenemos la raiz del XML
    		Element rootNode = document.getRootElement();
    		//obtenemos una lista de todos los hijos de mapa_navegacion con tag=rol 
    		List<Element> list = (List<Element>) rootNode.getChild("rol");
    		
    		for(int i = 0; i<list.size(); i++){//vamos recorriendo todos los hijos de mapa_navegacion
    			opcionResultadoYJSP = new HashMap<String, Map<String, String>>();
    			
    		
    		
    		
    		
    		
    			mapaDeNavegacion.put(list.get(0).getAttributeValue("rol"), opcionResultadoYJSP);
    		}
    		
    		
    		
    		
    	}catch ( IOException io ) {
            System.out.println( io.getMessage() );
        }catch ( JDOMException jdomex ) {
            System.out.println( jdomex.getMessage() );
        }
    	return mapaDeNavegacion;
    }

}
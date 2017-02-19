package uo.sdi.tests;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.*;

public class Sesion3Tests {

    private WebTester mary;


	@Before
    public void prepare() {
    	mary = new WebTester();
    	mary.setBaseUrl("http://localhost:8280/UO244588");
    }

    @Test
    public void testListadoDeCategorias() {
    	mary.beginAt("/login.jsp");
    	mary.assertTitleEquals("TaskManager - Inicie sesión");//comprobamos el nombre del titulo de la página
    	mary.assertTextPresent("identificador");//comprobamos texto de la página
    	mary.assertTextPresent("contr");//comprobamos texto de la página
    }

    @Test
    public void testIniciarSesionConExito() {
    
    }

    
    @Test
    public void testIniciarSesionSinExito() {
    	
    }

}
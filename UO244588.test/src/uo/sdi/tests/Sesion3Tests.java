package uo.sdi.tests;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.*;

public class Sesion3Tests {

	private WebTester mary;
	/*
	 * Ejecutar los test después de inicializar la base de datos con el script 
	 * de creación
	 * 
	 * Se necesita conexión a internet para poder ejecutar los tests
	 * 
	 * Elementos con menús desplegables (Javascript no se han podido hacer
	 * test de ellos)
	 * 
	 */
	@Before
	public void prepare() {
		mary = new WebTester();
		// desactivamos el scripting
		mary.setScriptingEnabled(false);
		mary.setBaseUrl("http://localhost:8280/UO244588");

	}

	@Test// test básico hasta log in
	public void testInicioSesionUsuario() {
		mary.beginAt("/login.jsp");
		//comprobamos el nombre del titulo de la página
		mary.assertTitleEquals("TaskManager - Inicie sesión");
		// comprobamos texto de la página comprobamos texto	de la página							 
		mary.assertTextPresent("Contraseña:");
		mary.assertTextPresent("Identificador de usuario:"); 
															

		/*
		 * Rellenamos los campos de datos
		 */
		mary.setTextField("login", "usuario1");
		mary.setTextField("password", "usuario1");
		mary.submit();

		mary.assertTitleEquals("TaskManager - Página principal del usuario");
	}

	@Test
	public void testInicioSesionAdmin() {
		mary.beginAt("/login.jsp");
		// comprobamos el  nombre del titulo de la página
		mary.assertTitleEquals("TaskManager - Inicie sesión");
		
		// comprobamos texto de la página comprobamos texto de la página
		mary.assertTextPresent("Contraseña:");
		mary.assertTextPresent("Identificador de usuario:");

		mary.setTextField("login", "administrador1");
		mary.setTextField("password", "administrador1");
		mary.submit();

		mary.assertTitleEquals("TaskManager - Administración de usuarios");

	}

	@Test
	public void testIniciarSesionSinExito() {
		mary.beginAt("/login.jsp");
		mary.assertTitleEquals("TaskManager - Inicie sesión");
		// comprobamos el  nombre del titulo de la página
		mary.assertTextPresent("Contraseña:");
		mary.assertTextPresent("Identificador de usuario:");

		/*
		 * Rellenamos los campos de datos
		 */
		mary.setTextField("login", "usuario1");
		// contraseña incorrecta
		mary.setTextField("password", "121223123");
		mary.submit();
		
		mary.assertTitleEquals("TaskManager - Inicie sesión");
	}

	@Test
	public void testCerrarSesionAdministrador() {
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "administrador1");
		mary.setTextField("password", "administrador1");
		mary.submit();

		// cerramos sesion
		mary.clickLink("cerrarSesion");
		mary.assertTitleEquals("TaskManager - Inicie sesión");

	}
	
	@Test
	public void testCerrarSesionUsuario() {
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "usuario1");
		mary.setTextField("password", "usuario1");
		mary.submit();
		mary.clickLink("cerrarSesion");// cerramos sesion

		mary.assertTitleEquals("TaskManager - Inicie sesión");

	}

	@Test// Solo se puede ejecutar una vez, hay que limpiar la base de datos
	public void testRegistrarseCorrectamenteUsuario() {
														
		mary.beginAt("/login.jsp");
		// comprobamos el nombre del titulo de la página
		mary.assertTitleEquals("TaskManager - Inicie sesión");
		
		// comprobamos texto de la página comprobamos texto de la página
		mary.assertTextPresent("Contraseña:");
		mary.assertTextPresent("Identificador de usuario:");

		mary.clickLink("registrarse");

		mary.assertTitleEquals("TaskManager - registrase");

		mary.setTextField("login", "agapito");
		mary.setTextField("email", "agapito@mail.es");
		mary.setTextField("password1", "agapito");
		mary.setTextField("password2", "agapito");
		mary.submit();

		mary.assertTitleEquals("TaskManager - Inicie sesión");

		mary.assertTextPresent("El registro se ha completado");

	}

	@Test
	public void testEliminarCategoriaDelSistema() {
		mary.beginAt("/login.jsp");
		// comprobamos el nombre del titulo de la página
		mary.assertTitleEquals("TaskManager - Inicie sesión");
		// comprobamos texto de la página comprobamos texto de la página
		mary.assertTextPresent("Contraseña:");
		mary.assertTextPresent("Identificador de usuario:");

		mary.setTextField("login", "usuario1");
		mary.setTextField("password", "usuario1");
		mary.submit();
		mary.assertTextPresent("¡Bienvenido al Task-Manager!");

	}
	
	@Test
	public void testHabilitarDeshabilitarUnusuario(){
		//Entramos en sesión como administrador
		mary.beginAt("/login.jsp");
		mary.setTextField("login", "administrador1");
		mary.setTextField("password", "administrador1");
		mary.submit();
		//vamos a deshabilitar al usuario1
		mary.assertLinkPresent("deshabilitarusuario3");
		mary.clickLink("deshabilitarusuario3");
		//comprobamos que esté el enlace para volver a habilitar
		mary.assertLinkPresent("habilitarusuario3");
		mary.clickLink("habilitarusuario3");
	}

	@Test
	public void testEntrarEnCategoriasDelSistema() {
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "usuario1");
		mary.setTextField("password", "usuario1");
		mary.submit();

		mary.submit("btnHoy");
		//hay que hacer algo identificativo de las categorias del sistema
		mary.assertTitleEquals("Task Manager - Listado de Tareas");
	}
	
	@Test
	public void testnuevaCategoria(){
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "usuario2");
		mary.setTextField("password", "usuario2");
		mary.submit();
		
		mary.setTextField("nombreCategoriaNueva", "CategoriaTest");
		mary.submit("crearCategoria");
		
		mary.assertTextFieldEquals("nombreCategoria_CategoriaTest", "CategoriaTest");
	}

	@Test
	public void testDuplicarCategoria(){
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "usuario2");
		mary.setTextField("password", "usuario2");
		mary.submit();
		
		mary.setTextField("nombreCategoriaNueva", "CategoriaTestDuplicar");
		mary.submit("crearCategoria");
		
		mary.assertTextFieldEquals("nombreCategoria_CategoriaTestDuplicar", "CategoriaTestDuplicar");
		
		mary.clickLink("duplicar_CategoriaTestDuplicar");
		mary.assertTextFieldEquals("nombreCategoria_CategoriaTestDuplicar - copy", "CategoriaTestDuplicar - copy");
	}


}
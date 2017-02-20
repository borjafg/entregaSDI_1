package uo.sdi.tests;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.*;

public class Sesion3Tests {

	private WebTester mary;

	@Before
	public void prepare() {
		mary = new WebTester();
		mary.setScriptingEnabled(false);// desactivamos el scripting
		mary.setBaseUrl("http://localhost:8280/UO244588");

	}

	@Test
	public void testInicioSesionUsuario() {// test básico hasta log in
		mary.beginAt("/login.jsp");
		mary.assertTitleEquals("TaskManager - Inicie sesión");// comprobamos el
																// nombre del
																// titulo de la
																// página
		mary.assertTextPresent("Contraseña:");// comprobamos texto de la página
		mary.assertTextPresent("Identificador de usuario:");// comprobamos texto
															// de la página

		/*
		 * Rellenamos los campos de datos
		 */
		mary.setTextField("login", "mary");
		mary.setTextField("password", "mary123");
		mary.submit();

		mary.assertTitleEquals("TaskManager - Página principal del usuario");
	}

	@Test
	public void testInicioSesionAdmin() {
		mary.beginAt("/login.jsp");
		mary.assertTitleEquals("TaskManager - Inicie sesión");// comprobamos el
																// nombre del
																// titulo de la
																// página
		mary.assertTextPresent("Contraseña:");// comprobamos texto de la página
		mary.assertTextPresent("Identificador de usuario:");// comprobamos texto
															// de la página

		mary.setTextField("login", "admin");
		mary.setTextField("password", "admin123");
		mary.submit();

		mary.assertTitleEquals("TaskManager - Administración de usuarios");

	}

	@Test
	public void testIniciarSesionSinExito() {
		mary.beginAt("/login.jsp");
		mary.assertTitleEquals("TaskManager - Inicie sesión");// comprobamos el
																// nombre del
																// titulo de la
																// página
		mary.assertTextPresent("Contraseña:");// comprobamos texto de la página
		mary.assertTextPresent("Identificador de usuario:");// comprobamos texto
															// de la página

		/*
		 * Rellenamos los campos de datos
		 */
		mary.setTextField("login", "mary");
		mary.setTextField("password", "121223123");// contraseña incorrecta
		mary.submit();

		mary.assertTitleEquals("TaskManager - Inicie sesión");
	}

	@Test
	public void testCerrarSesion() {
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "admin");
		mary.setTextField("password", "admin123");
		mary.submit();

		mary.clickLink("cerrarSesion");// cerramos sesion

		mary.assertTitleEquals("TaskManager - Inicie sesión");// comprobamos que
																// hemos vuelto
																// a las pestaña
																// de

	}

	@Test
	public void testRegistrarseCorrectamenteUsuario() {// Solo se puede ejecutar
														// una vez, hay que
														// limpiar la base de
														// datos
		mary.beginAt("/login.jsp");
		mary.assertTitleEquals("TaskManager - Inicie sesión");// comprobamos el
																// nombre del
																// titulo de la
																// página
		mary.assertTextPresent("Contraseña:");// comprobamos texto de la página
		mary.assertTextPresent("Identificador de usuario:");// comprobamos texto
															// de la página

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
		mary.assertTitleEquals("TaskManager - Inicie sesión");// comprobamos el
																// nombre del
																// titulo de la
																// página
		mary.assertTextPresent("Contraseña:");// comprobamos texto de la página
		mary.assertTextPresent("Identificador de usuario:");// comprobamos texto
															// de la página

		mary.setTextField("login", "mary");
		mary.setTextField("password", "mary123");
		mary.submit();
		mary.assertTextPresent("¡Bienvenido al Task-Manager!");

	}

	@Test
	public void testEntrarEnCategoriasDelSistema() {
		mary.beginAt("/login.jsp");

		mary.setTextField("login", "mary");
		mary.setTextField("password", "mary123");
		mary.submit();

		mary.clickButton("btnInbox");

		mary.assertTitleEquals("Task Manager - Listado de Tareas");// hay que
																	// hacer
																	// algo
																	// identificativo
																	// de las
																	// categorias
																	// del
																	// sistema
	}

}
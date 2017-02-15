package uo.sdi;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import alb.util.date.DateUtil;

/**
 * Fuerza a que se cargen los parametros de configuracion, se analizen todos los
 * mapeos y, si procede, se crea la BDD
 * 
 */
public class LoadEntityManagerFactory {

    public static void main(String[] args) {
	EntityManagerFactory emf = Persistence
		.createEntityManagerFactory("task_manager");

	EntityManager ent = emf.createEntityManager();
	EntityTransaction trx = ent.getTransaction();
	trx.begin();

	imprimirPorPantalla("======================", null);

	// =================================
	// Clases del modelo de dominio
	// =================================

	User user = ent.find(User.class, 1L);
	imprimirPorPantalla("Usuario (id = 1)", user);

	Task task = ent.find(Task.class, 2L);
	imprimirPorPantalla("Tarea (id = 2)", task);

	Category category = ent.find(Category.class, 1L);
	imprimirPorPantalla("Categoria (id = 1)", category);

	// ====================================
	// Consultas del fichero ORM
	// ====================================

	// ============= (1) =============

	List<Task> tareasSinCategoria = ent
		.createNamedQuery("Task.findInboxTasksByUserId", Task.class)
		.setParameter("userId", 1L).getResultList();

	imprimirPorPantalla("Tareas sin categoria (usuario id = 1)",
		tareasSinCategoria);

	// ============= (2) =============

	List<Task> tareasParaHoy = ent
		.createNamedQuery("Task.findInboxTasksByUserId", Task.class)
		.setParameter("userId", 1L).getResultList();

	imprimirPorPantalla("Tareas para hoy (usuario id = 1)", tareasParaHoy);

	// ============= (3) =============

	Date fecha = DateUtil.addDays(DateUtil.now(), 7);

	List<Task> tareasSemana = ent
		.createNamedQuery("Task.findWeekTasksByUserId", Task.class)
		.setParameter("userId", 1L).setParameter("fechaSemana", fecha)
		.getResultList();

	imprimirPorPantalla("Tareas para la semana (usuario id = 1)",
		tareasSemana);

	trx.commit();
	ent.close();
	emf.close();

	System.out.println("--> Si no hay excepciones todo va bien");
	System.out.println("\n\t (O no hay ninguna clase mapeada)");
    }

    private static void imprimirPorPantalla(String mensaje, Object valor) {
	System.out.println(mensaje);

	if (valor != null) {
	    // Si el valor es una lista de objetos
	    if (valor instanceof List<?>) {
		System.out.println();
		imprimirLista((List<?>) valor);
	    }

	    else {
		System.out.println();
		System.out.println(valor);
		System.out.println();
	    }

	    System.out.println("======================");
	}
    }

    private static void imprimirLista(List<?> lista) {
	for (Object elemento : lista) {
	    System.out.println("---> " + elemento);
	    System.out.println();
	}
    }

}
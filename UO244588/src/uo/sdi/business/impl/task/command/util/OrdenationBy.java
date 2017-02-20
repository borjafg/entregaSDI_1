package uo.sdi.business.impl.task.command.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uo.sdi.model.Task;
import alb.util.date.DateUtil;

public class OrdenationBy {

    /**
     * Ordena una lista de tareas en función de la fecha en la que fueron
     * creadas.
     * 
     * @param listaTareasNoOrdenadas
     *            Lista de tareas que vamos a ordenar según la fecha de creacion
     * 
     * @return la lista ordenanda según la fecha de creación
     * 
     */
    public static List<Task> orderByCreationDate(
	    List<Task> listaTareasNoOrdenadas) {

	Collections.sort(listaTareasNoOrdenadas, new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
		if (DateUtil.isAfter(o1.getCreated(), o2.getCreated())) {
		    return 1;
		}

		else if (DateUtil.isBefore(o1.getCreated(), o2.getCreated())) {
		    return -1;
		}

		else {
		    return 0;
		}
	    }
	});

	return listaTareasNoOrdenadas;
    }

    /**
     * Ordena una lista de tareas en función de la fecha para la que están
     * planificadas.
     * 
     * @param listaTareasNoOrdenadas
     *            Lista de tareas que vamos a ordenar según la fecha de
     *            planificación.
     * 
     * @return la lista ordenanda según la fecha de planificación.
     * 
     */
    public static List<Task> orderByPlannedDate(
	    List<Task> listaTareasNoOrdenadas) {

	Collections.sort(listaTareasNoOrdenadas, new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
		if (DateUtil.isAfter(o1.getPlanned(), o2.getPlanned())) {
		    return 1;
		}

		else if (DateUtil.isBefore(o1.getPlanned(), o2.getPlanned())) {
		    return -1;
		}

		else {
		    return 0;
		}
	    }

	});

	return listaTareasNoOrdenadas;
    }

    /**
     * Ordena una lista de tareas en función de la fecha para la que están
     * planificadas. Las fechas anteriores al día de hoy son las últimas y si
     * coincide el día se usa el nombre para ordenar.
     * 
     * @param listaTareasNoOrdenadas
     *            Lista de tareas que vamos a ordenar según la fecha de
     *            planificación.
     * 
     * @return la lista ordenanda según la fecha de planificación.
     * 
     */
    public static List<Task> orderByPlannedDateAndName(
	    List<Task> tareasNoOrdenadas) {

	Collections.sort(tareasNoOrdenadas, new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
		if (DateUtil.isAfter(o1.getPlanned(), o2.getPlanned())) {
		    return 1;
		}

		else if (DateUtil.isBefore(o1.getPlanned(), o2.getPlanned())) {
		    return -1;
		}

		else {
		    return o1.getTitle().compareTo(o2.getTitle());
		}
	    }
	});

	return tareasNoOrdenadas;
    }

}
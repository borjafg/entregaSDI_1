package uo.sdi.business.impl.task.command.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import alb.util.date.DateUtil;
import uo.sdi.model.Task;

public class OrdenationBy {

	/**
	 * @param listaTareasNoOrdenadas
	 *            , lista de tareas que vamos a ordenar según la fecha de
	 *            creacion
	 * @return la lista ordenanda List<Task>, según la fecha de creación
	 */
	public static List<Task> orderByCreationDate(
			List<Task> listaTareasNoOrdenadas) {

		Collections.sort(listaTareasNoOrdenadas, new Comparator<Task>() {

			@Override
			public int compare(Task o1, Task o2) {

				if (DateUtil.isAfter(o1.getCreated(), o2.getCreated())) {
					return 1;
				} else if (DateUtil.isBefore(o1.getCreated(), o2.getCreated())) {
					return -1;
				} else {
					return 0;
				}

			}

		});

		return listaTareasNoOrdenadas;

	}

	/**
	 * @param listaTareasNoOrdenadas
	 *            , lista de tareas que vamos a ordenar según la fecha de
	 *            asignación
	 * @return la lista ordenada (List<Task>) según su fecha de asignación
	 */
	public static List<Task> orderByProvidedDate(
			List<Task> listaTareasNoOrdenadas) {

		Collections.sort(listaTareasNoOrdenadas, new Comparator<Task>() {

			@Override
			public int compare(Task o1, Task o2) {
				if (DateUtil.isAfter(o1.getPlanned(), o2.getPlanned())) {
					return 1;
				} else if (DateUtil.isBefore(o1.getPlanned(), o2.getPlanned())) {
					return -1;
				} else {
					return 0;
				}

			}

		});

		return listaTareasNoOrdenadas;

	}

}

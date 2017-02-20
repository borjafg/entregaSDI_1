package uo.sdi.acciones.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uo.sdi.model.Task;

public class OrdenationWeek {

    public static List<Task> sort(List<Task> tasks) {
	Collections.sort(tasks, new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
		int aux = OrdenationCommon.compareToWithNulls(o1.getPlanned(),
			o2.getPlanned(), true);

		if (aux == 0) {
		    return OrdenationCommon.compareToWithNulls(o1.getCategory(),
			    o2.getCategory());
		}

		return aux;
	    }
	});

	return tasks;
    }

}
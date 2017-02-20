package uo.sdi.acciones.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import uo.sdi.model.Task;

public class OrdenationInbox_And_User {

    public static void sortWhitoutFinishedTasks(List<Task> tasks) {
	Collections.sort(tasks, new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
		return OrdenationCommon.compareToWithNulls(o1.getPlanned(),
			o2.getPlanned(), true);
	    }
	});
    }

    public static void sortWhitFinishedTasks(List<Task> tasks) {
	Collections.sort(tasks, new Comparator<Task>() {

	    @Override
	    public int compare(Task o1, Task o2) {
		if (o1.getFinished() == null) {
		    if (o2.getFinished() == null) {
			return OrdenationCommon.compareToWithNulls(
				o1.getPlanned(), o2.getPlanned(), true);
		    }

		    return -1; // Tarea 2 finalizada (va al final de la lista)
		}

		else { // tarea 1 finalizada
		    if (o2.getFinished() == null) {
			return 1;
		    }

		    // tarea 2 finalizada
		    return OrdenationCommon.compareToWithNulls(o1.getPlanned(),
			    o2.getPlanned(), false);
		}
	    }
	});
    }

}
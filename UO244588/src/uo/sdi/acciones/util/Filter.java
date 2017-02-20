package uo.sdi.acciones.util;

import java.util.Iterator;
import java.util.List;

import uo.sdi.model.Task;

public class Filter {

    private Filter() {
	
    }
    
    public static void removeFinishedTasks(List<Task> tasks) {
	for (Iterator<Task> iterator = tasks.iterator(); iterator.hasNext();) {
	    Task tarea = iterator.next();

	    if (tarea.getFinished() != null) {
		iterator.remove();
	    }
	}
    }

}
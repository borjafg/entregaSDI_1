package uo.sdi.business.impl.task.command;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.util.Jpa;

public class DeleteCategoryCommand implements Command<Void> {

    private Long catId;

    public DeleteCategoryCommand(Long catId) {
	this.catId = catId;
    }

    @Override
    public Void execute() throws BusinessException {
	Category category = CategoryFinder.findById(catId);

	User user = category.getUser();

	// Eliminar tareas del usuario que pertenezcan a esa categoria
	for (Task tarea : category.getTasks()) {
	    user.desvincularTarea(tarea);
	    Jpa.getManager().remove(tarea);
	}

	user.desvincularCategoria(category);
	Jpa.getManager().remove(category);

	return null;
    }

}
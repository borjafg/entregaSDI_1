package uo.sdi.business.impl.task.command;

import java.util.Date;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.TaskCheck;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;
import uo.sdi.persistence.util.Jpa;

public class UpdateTaskCommand implements Command<Void> {

    private Long idTask;
    private String name;
    private String comment;
    private Date planned;
    private Long idCateg;

    public UpdateTaskCommand(Long idTask, String name, String comment,
	    Date planned, Long idCateg) {

	this.idTask = idTask;
	this.name = name;
	this.comment = comment;
	this.planned = planned;
	this.idCateg = idCateg;
    }

    @Override
    public Void execute() throws BusinessException {
	Task task = TaskFinder.findById(idTask);

	task.setTittle(name);

	TaskCheck.titleIsNotNull(task);
	TaskCheck.titleIsNotEmpty(task);

	if (idCateg != null) {
	    Category categ = CategoryFinder.findById(idCateg);

	    task.setCategory(categ);
	}

	task.setComments(comment);
	task.setPlanned(planned);

	Jpa.getManager().merge(task);

	return null;
    }

}
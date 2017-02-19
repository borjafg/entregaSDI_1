package uo.sdi.business.impl.task.command;

import alb.util.date.DateUtil;
import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.TaskCheck;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class CreateTaskCommand implements Command<Task> {

    private String name;
    private boolean forToday;
    private Long idUser;
    private Long idCateg;

    public CreateTaskCommand(String name, boolean forToday, Long idUser,
	    Long idCateg) {

	this.name = name;
	this.forToday = forToday;
	this.idUser = idUser;
	this.idCateg = idCateg;
    }

    @Override
    public Task execute() throws BusinessException {
	User user = UserFinder.findById(idUser);

	BusinessCheck.isNotNull(user, "El usuario no existe");

	Task task = new Task(name, user);

	if (forToday) {
	    task.setPlanned(DateUtil.today());
	}

	TaskCheck.userExists(task);
	TaskCheck.userIsNotDisabled(task);
	TaskCheck.userIsNotAdmin(task);
	TaskCheck.titleIsNotNull(task);
	TaskCheck.titleIsNotEmpty(task);

	if (idCateg != null) {
	    Category categ = CategoryFinder.findById(idCateg);
	    BusinessCheck.isNotNull(categ, "La categoria no existe");
	    task.setCategory(categ);
	}

	task.setFinished(null);

	Jpa.getManager().persist(task);

	return task;
    }

}
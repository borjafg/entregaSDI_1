package uo.sdi.business.impl.task.command;

import alb.util.date.DateUtil;
import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.TaskCheck;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class CreateTaskCommand implements Command<Task> {

    private String name;
    private boolean forToday;
    private Long idUser;

    public CreateTaskCommand(String name, boolean forToday, Long idUser) {
	this.name = name;
	this.forToday = forToday;
	this.idUser = idUser;
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

	if (task.getCategory().getId() != null) {
	    TaskCheck.categoryExists(task);
	}

	task.setFinished(null);

	Jpa.getManager().persist(task);

	return task;
    }

}
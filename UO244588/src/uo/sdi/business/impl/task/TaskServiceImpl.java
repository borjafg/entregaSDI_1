package uo.sdi.business.impl.task;

import java.util.Date;
import java.util.List;

import uo.sdi.business.TaskService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.command.CommandExecutor;
import uo.sdi.business.impl.task.command.CreateCategoryCommand;
import uo.sdi.business.impl.task.command.CreateTaskCommand;
import uo.sdi.business.impl.task.command.DeleteCategoryCommand;
import uo.sdi.business.impl.task.command.DeleteTaskCommand;
import uo.sdi.business.impl.task.command.DuplicateCategoryCommand;
import uo.sdi.business.impl.task.command.FindCategoriesByUserIdCommand;
import uo.sdi.business.impl.task.command.MarkTaskAsFinishedCommand;
import uo.sdi.business.impl.task.command.UpdateCategoryCommand;
import uo.sdi.business.impl.task.command.UpdateTaskCommand;
import uo.sdi.business.impl.task.command.FindTasksByCategoryId;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.TaskFinder;

public class TaskServiceImpl implements TaskService {

    @Override
    public Long createCategory(String name, Long idUser)
	    throws BusinessException {

	Category categ = new CommandExecutor<Category>()
		.execute(new CreateCategoryCommand(name, idUser));

	return categ.getId();
    }

    @Override
    public Long duplicateCategory(Long id) throws BusinessException {
	Category categ = new CommandExecutor<Category>()
		.execute(new DuplicateCategoryCommand(id));

	return categ.getId();
    }

    @Override
    public void updateCategory(Long id, String name) throws BusinessException {
	new CommandExecutor<Void>()
		.execute(new UpdateCategoryCommand(id, name));
    }

    @Override
    public void deleteCategory(Long catId, Long idUser)
	    throws BusinessException {
	new CommandExecutor<Void>().execute(new DeleteCategoryCommand(catId,
		idUser));
    }

    @Override
    public Category findCategoryById(final Long id) throws BusinessException {
	return new CommandExecutor<Category>().execute(new Command<Category>() {
	    @Override
	    public Category execute() throws BusinessException {
		return CategoryFinder.findById(id);
	    }
	});
    }

    @Override
    public List<Category> findCategoriesByUserId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Category>>()
		.execute(new FindCategoriesByUserIdCommand(id));
    }

    @Override
    public Long createTask(String name, boolean forToday, Long idUser,
	    Long idCateg) throws BusinessException {

	Task tarea = new CommandExecutor<Task>().execute(new CreateTaskCommand(
		name, forToday, idUser, idCateg));

	return tarea.getId();
    }

    @Override
    public void deleteTask(final Long id) throws BusinessException {
	new CommandExecutor<Void>().execute(new DeleteTaskCommand(id));
    }

    @Override
    public void markTaskAsFinished(Long id) throws BusinessException {
	new CommandExecutor<Void>().execute(new MarkTaskAsFinishedCommand(id));
    }

    @Override
    public void updateTask(Long idTask, String name, String comment,
	    Date planned, Long idCateg) throws BusinessException {

	new CommandExecutor<Void>().execute(new UpdateTaskCommand(idTask, name,
		comment, planned, idCateg));
    }

    @Override
    public Task findTaskById(final Long id) throws BusinessException {
	return new CommandExecutor<Task>().execute(new Command<Task>() {

	    @Override
	    public Task execute() throws BusinessException {
		return TaskFinder.findById(id);
	    }
	});
    }

    @Override
    public List<Task> findInboxTasksByUserId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new Command<List<Task>>() {

		    @Override
		    public List<Task> execute() throws BusinessException {
			return TaskFinder.findInboxTasksByUserId(id);
		    }
		});
    }

    @Override
    public List<Task> findWeekTasksByUserId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new Command<List<Task>>() {

		    @Override
		    public List<Task> execute() throws BusinessException {
			return TaskFinder.findWeekTasksByUserId(id);
		    }
		});
    }

    @Override
    public List<Task> findTodayTasksByUserId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new Command<List<Task>>() {

		    @Override
		    public List<Task> execute() throws BusinessException {
			return TaskFinder.findTodayTasksByUserId(id);
		    }
		});
    }

    @Override
    public List<Task> findTasksByCategoryId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new FindTasksByCategoryId(id));
    }

    @Override
    public List<Task> findUnfinishedTasksByCategoryId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new Command<List<Task>>() {

		    @Override
		    public List<Task> execute() throws BusinessException {
			return TaskFinder.findUnfinishedTasksByCategoryId(id);
		    }
		});
    }

    @Override
    public List<Task> findFinishedTasksByCategoryId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new Command<List<Task>>() {

		    @Override
		    public List<Task> execute() throws BusinessException {
			return TaskFinder.findFinishedTasksByCategoryId(id);
		    }
		});
    }

    @Override
    public List<Task> findFinishedInboxTasksByUserId(final Long id)
	    throws BusinessException {

	return new CommandExecutor<List<Task>>()
		.execute(new Command<List<Task>>() {

		    @Override
		    public List<Task> execute() throws BusinessException {
			return TaskFinder.findFinishedTasksInboxByUserId(id);
		    }
		});
    }

}
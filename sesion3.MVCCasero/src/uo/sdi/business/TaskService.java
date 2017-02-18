package uo.sdi.business;

import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Category;
import uo.sdi.model.Task;

public interface TaskService {

    public Long createCategory(Category category) throws BusinessException;

    public Long duplicateCategory(Long id) throws BusinessException;

    public void updateCategory(Category category) throws BusinessException;

    public void deleteCategory(Long id) throws BusinessException;

    public Category findCategoryById(Long id) throws BusinessException;

    public List<Category> findCategoriesByUserId(Long id)
	    throws BusinessException;

    public Long createTask(Task task) throws BusinessException;

    public void deleteTask(Long id) throws BusinessException;

    public void markTaskAsFinished(Long id) throws BusinessException;

    public void updateTask(Task task) throws BusinessException;

    public Task findTaskById(Long id) throws BusinessException;

    public List<Task> findInboxTasksByUserId(Long id) throws BusinessException;

    public List<Task> findWeekTasksByUserId(Long id) throws BusinessException;

    public List<Task> findTodayTasksByUserId(Long id) throws BusinessException;

    public List<Task> findTasksByCategoryIdOrderByCreationDate(Long catId)
	    throws BusinessException;

    public List<Task> findUnfinishedTasksByCategoryId(Long id)
	    throws BusinessException;

    public List<Task> findFinishedTasksByCategoryId(Long catId)
	    throws BusinessException;

    public List<Task> findFinishedInboxTasksByUserId(Long userId)
	    throws BusinessException;

}

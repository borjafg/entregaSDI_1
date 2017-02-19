package uo.sdi.business;

import java.util.Date;
import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Category;
import uo.sdi.model.Task;

public interface TaskService {

    public Long createCategory(String name, Long idUser)
	    throws BusinessException;

    public Long duplicateCategory(Long id) throws BusinessException;

    public void updateCategory(Long id, String name) throws BusinessException;

    public void deleteCategory(Long idCategory,Long idUser) throws BusinessException;

    public Category findCategoryById(Long id) throws BusinessException;

    public List<Category> findCategoriesByUserId(Long id)
	    throws BusinessException;

    public Long createTask(String name, boolean forToday, Long idUser,
	    Long idCateg) throws BusinessException;

    public void deleteTask(Long id) throws BusinessException;

    public void markTaskAsFinished(Long id) throws BusinessException;

    public void updateTask(Long idTask, String name, String comment,
	    Date planned, Long idCateg) throws BusinessException;

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

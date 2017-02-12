package uo.sdi.business.impl.util;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;

public class TaskCheck {

    public static void categoryExists(Task task) throws BusinessException {
	Category c = CategoryFinder.findById(task.getCategory().getId());
	BusinessCheck.isNotNull(c, "La categoria no existe");
    }

    public static void userExists(Task task) throws BusinessException {
	User u = UserFinder.findById(task.getUser().getId());
	BusinessCheck.isNotNull(u, "El usuario de la tarea no existe");
    }

    public static void userIsNotDisabled(Task task) throws BusinessException {
	User u = UserFinder.findById(task.getUser().getId());
	BusinessCheck.isTrue(u.getStatus().equals(UserStatus.ENABLED),
		"El usuario de la tarea esta deshabilitado");
    }

    public static void userIsNotAdmin(Task task) throws BusinessException {
	User u = UserFinder.findById(task.getUser().getId());
	BusinessCheck.isFalse(u.getIsAdmin(),
		"El usuario de la tarea no puede ser un administrador");
    }

    public static void titleIsNotNull(Task task) throws BusinessException {
	BusinessCheck.isTrue(task.getTitle() != null,
		"El titulo de la tarea no puede ser null");
    }

    public static void titleIsNotEmpty(Task task) throws BusinessException {
	BusinessCheck.isTrue(!"".equals(task.getTitle()),
		"El titulo de la tarea no puede estar vacio");
    }

}
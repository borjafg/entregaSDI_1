package uo.sdi.business.impl.util;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.Category;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;

public class CategoryCheck {

    public static void nameIsNotNull(Category category)
	    throws BusinessException {
	BusinessCheck.isNotNull(category.getName(),
		"Debe indicarse el nombre de la categoria");
    }

    public static void nameIsNotEmpty(Category category)
	    throws BusinessException {
	BusinessCheck.isFalse(category.getName().length() == 0,
		"Debe indicarse el nombre de la categoria");
    }

    public static void userIsNotNull(Category category)
	    throws BusinessException {
	BusinessCheck.isNotNull(category.getUser(),
		"Una categoria debe ser asignada a un usuario");
    }

    public static void isValidUser(Category c) throws BusinessException {
	User u = UserFinder.findById(c.getUser().getId());

	BusinessCheck.isNotNull(u,
		"Una categoria debe asignarse a un usuario existente");

	BusinessCheck.isTrue(u.getStatus().equals(UserStatus.ENABLED),
		"Una categoria debe asignarse a un usuario habilitado");
    }

    public static void isUniqueName(String category, Long idUser)
	    throws BusinessException {
	Category other = CategoryFinder.findByUserIdAndName(idUser, category);

	BusinessCheck.isNull(other, "El nombre de la categoria no se puede "
		+ "repetir para este usuario");
    }

    public static void isNotForAdminUser(Category category)
	    throws BusinessException {
	User u = UserFinder.findById(category.getUser().getId());
	BusinessCheck.isFalse(u.getIsAdmin(),
		"Un admin no puede tener categorias");
    }

}
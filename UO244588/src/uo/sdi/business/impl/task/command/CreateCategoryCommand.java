package uo.sdi.business.impl.task.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.CategoryCheck;
import uo.sdi.model.Category;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class CreateCategoryCommand implements Command<Category> {

    private String name;
    private Long idUser;

    public CreateCategoryCommand(String name, Long idUser) {
	this.name = name;
	this.idUser = idUser;
    }

    @Override
    public Category execute() throws BusinessException {
	User user = UserFinder.findById(idUser);

	BusinessCheck.isNotNull(user, "El usuario no existe.");
	CategoryCheck.isUniqueName(name, idUser);

	Category category = new Category(user);
	category.setName(name);

	CategoryCheck.nameIsNotNull(category);
	CategoryCheck.nameIsNotEmpty(category);
	CategoryCheck.userIsNotNull(category);
	CategoryCheck.isValidUser(category);
	CategoryCheck.isNotForAdminUser(category);

	Jpa.getManager().persist(category);

	return category;
    }

}
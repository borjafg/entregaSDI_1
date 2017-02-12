package uo.sdi.business.impl.task.command;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.CategoryCheck;
import uo.sdi.model.Category;
import uo.sdi.persistence.util.Jpa;

public class CreateCategoryCommand implements Command<Category> {

    private Category category;

    public CreateCategoryCommand(Category category) {
	this.category = category;
    }

    @Override
    public Category execute() throws BusinessException {
	CategoryCheck.nameIsNotNull(category);
	CategoryCheck.nameIsNotEmpty(category);
	CategoryCheck.userIsNotNull(category);
	CategoryCheck.isValidUser(category);
	CategoryCheck.isUniqueName(category);
	CategoryCheck.isNotForAdminUser(category);

	Jpa.getManager().persist(category);

	return category;
    }

}
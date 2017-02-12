package uo.sdi.business.impl.task.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.CategoryCheck;
import uo.sdi.model.Category;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.util.Jpa;

public class UpdateCategoryCommand implements Command<Void> {

    private Category category;

    public UpdateCategoryCommand(Category category) {
	this.category = category;
    }

    @Override
    public Void execute() throws BusinessException {
	Category previous = CategoryFinder.findById(category.getId());

	checkCategoryExists(previous);
	CategoryCheck.nameIsNotNull(category);
	CategoryCheck.nameIsNotEmpty(category);

	if (nameIsChanged(previous, category)) {
	    CategoryCheck.isUniqueName(category);
	}

	checkUserIsNotChanged(previous, category);
	
	Jpa.getManager().merge(category);

	return null;
    }

    private void checkUserIsNotChanged(Category previous, Category current)
	    throws BusinessException {
	BusinessCheck.isTrue(
		previous.getUser().getId().equals(current.getUser().getId()),
		"Una categoria no puede ser cambiada de usuario");
    }

    private boolean nameIsChanged(Category previous, Category current) {
	return !previous.getName().equals(current.getName());
    }

    private void checkCategoryExists(Category c) throws BusinessException {
	BusinessCheck.isNotNull(c, "La categoria no existe");
    }

}
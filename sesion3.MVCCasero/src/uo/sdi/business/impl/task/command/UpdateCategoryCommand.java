package uo.sdi.business.impl.task.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.CategoryCheck;
import uo.sdi.model.Category;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.util.Jpa;

public class UpdateCategoryCommand implements Command<Void> {

    private Long id;
    private String name;

    public UpdateCategoryCommand(Long id, String name) {
	this.id = id;
	this.name = name;
    }

    @Override
    public Void execute() throws BusinessException {
	Category category = CategoryFinder.findById(id);

	BusinessCheck.isNotNull(category, "La categoria no existe");

	String previousName = category.getName();
	category.setName(name);

	CategoryCheck.nameIsNotNull(category);
	CategoryCheck.nameIsNotEmpty(category);

	if (!previousName.equals(name)) {
	    CategoryCheck.isUniqueName(category);
	}

	Jpa.getManager().merge(category);

	return null;
    }

}
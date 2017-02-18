package uo.sdi.business.impl.task.command;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.persistence.CategoryFinder;

public class FindTasksByCategoryIdOrderByCreationDate implements
	Command<List<Task>> {

    private Long id;

    public FindTasksByCategoryIdOrderByCreationDate(Long id) {
	this.id = id;
    }

    @Override
    public List<Task> execute() throws BusinessException {
	Category categ = CategoryFinder.findById(id);

	BusinessCheck.isNotNull(categ, "La categoria no existe.");

	return new ArrayList<Task>(categ.getTasks());
    }

}
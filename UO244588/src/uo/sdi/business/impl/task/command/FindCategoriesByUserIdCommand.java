package uo.sdi.business.impl.task.command;

import java.util.List;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.Category;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;

public class FindCategoriesByUserIdCommand implements Command<List<Category>> {

    private Long userId;

    public FindCategoriesByUserIdCommand(Long userId) {
	this.userId = userId;
    }

    @Override
    public List<Category> execute() throws BusinessException {
	User user = UserFinder.findById(userId);

	BusinessCheck.isNotNull(userId, "El usuario no existe");

	return CategoryFinder.findByUserId(user.getId());
    }

}
package uo.sdi.business.impl.admin.command;

import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

public class FindAllUsers implements Command<List<User>> {

    @Override
    public List<User> execute() throws BusinessException {
	return UserFinder.findAll();
    }

}
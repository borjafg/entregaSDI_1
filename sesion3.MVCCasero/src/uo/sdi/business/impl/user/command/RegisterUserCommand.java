package uo.sdi.business.impl.user.command;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.UserCheck;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class RegisterUserCommand implements Command<User> {

    private User user;

    public RegisterUserCommand(User user) {
	this.user = user;
    }

    @Override
    public User execute() throws BusinessException {
	UserCheck.isNotAdmin(user);
	UserCheck.isValidEmailSyntax(user);
	UserCheck.minLoginLength(user);
	UserCheck.minPasswordLength(user);
	UserCheck.notRepeatedLogin(user);

	Jpa.getManager().persist(user);

	return user;
    }

}
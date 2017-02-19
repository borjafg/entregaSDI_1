package uo.sdi.business.impl.user;

import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.CommandExecutor;
import uo.sdi.business.impl.user.command.FindLoggableUSerCommand;
import uo.sdi.business.impl.user.command.RegisterUserCommand;
import uo.sdi.business.impl.user.command.UpdateUserDetailsCommand;
import uo.sdi.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public void registerUser(String login, String email, String password1,
	    String password2) throws BusinessException {

	new CommandExecutor<Void>().execute(new RegisterUserCommand(login,
		email, password1, password2));
    }

    @Override
    public void updateUserDetails(User user) throws BusinessException {
	new CommandExecutor<Void>().execute(new UpdateUserDetailsCommand(user));
    }

    @Override
    public User findLoggableUser(final String login, final String password)
	    throws BusinessException {

	return new CommandExecutor<User>().execute(new FindLoggableUSerCommand(
		login, password));
    }

}
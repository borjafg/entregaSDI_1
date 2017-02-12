package uo.sdi.business.impl.user.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.UserCheck;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class UpdateUserDetailsCommand implements Command<Void> {

    private User user;

    public UpdateUserDetailsCommand(User user) {
	this.user = user;
    }

    @Override
    public Void execute() throws BusinessException {
	User previous = UserFinder.findById(user.getId());

	checkUserExist(previous);
	checkStatusIsNotChanged(previous, user);
	checkIsAdminNotChanged(previous, user);
	UserCheck.isValidEmailSyntax(user);
	UserCheck.minLoginLength(user);
	UserCheck.minPasswordLength(user);

	if (loginIsChanged(previous, user)) {
	    UserCheck.notRepeatedLogin(user);
	}

	Jpa.getManager().merge(user);
	return null;
    }

    private void checkIsAdminNotChanged(User previous, User current)
	    throws BusinessException {
	BusinessCheck.isTrue(isAdminNotChanged(previous, current),
		"Un usuario no puede pasar a ser admin o dejar de serlo");
    }

    private void checkUserExist(User previous) throws BusinessException {
	BusinessCheck.isNotNull(previous, "El usuario no existe");
    }

    private void checkStatusIsNotChanged(User previous, User current)
	    throws BusinessException {
	BusinessCheck.isTrue(statusIsNotChanged(previous, current),
		"Solo el administrador puede cambiar el status");
    }

    private boolean statusIsNotChanged(User previous, User current) {
	return previous.getStatus().equals(current.getStatus());
    }

    private boolean loginIsChanged(User previous, User current) {
	return !previous.getLogin().equals(current.getLogin());
    }

    private boolean isAdminNotChanged(User previous, User current) {
	return previous.getIsAdmin() == current.getIsAdmin();
    }

}
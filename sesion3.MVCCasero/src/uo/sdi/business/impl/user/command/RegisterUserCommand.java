package uo.sdi.business.impl.user.command;

import uo.sdi.business.exception.BusinessCheck;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.util.UserCheck;
import uo.sdi.model.User;
import uo.sdi.persistence.util.Jpa;

public class RegisterUserCommand implements Command<Void> {

    private String login;
    private String email;
    private String password1;
    private String password2;

    public RegisterUserCommand(String login, String email, String password1,
	    String password2) {

	this.login = login;
	this.email = email;
	this.password1 = password1;
	this.password2 = password2;
    }

    @Override
    public Void execute() throws BusinessException {
	BusinessCheck.isTrue(password1.equals(password2),
		"Las contraseñas no coinciden.");

	User user = new User(login);

	user.setEmail(email);
	user.setPassword(password1);

	UserCheck.isNotAdmin(user);
	UserCheck.isValidEmailSyntax(user);
	UserCheck.minLoginLength(user);
	UserCheck.minPasswordLength(user);
	UserCheck.notRepeatedLogin(user);

	Jpa.getManager().persist(user);

	return null;
    }

}
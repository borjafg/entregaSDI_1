package uo.sdi.business;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.model.User;

public interface UserService {

    public void registerUser(String login, String email, String password1,
	    String password2) throws BusinessException;

    public void updateUserDetails(User user) throws BusinessException;

    public User findLoggableUser(String login, String password)
	    throws BusinessException;

}
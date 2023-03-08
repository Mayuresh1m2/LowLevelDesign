package covidtracker.handler;

import covidtracker.dao.UserDao;
import covidtracker.exception.UserAlreadyExistsException;
import covidtracker.exception.UserDoesNotExistsException;
import covidtracker.user.User;

public class RegisterHandler {
    UserDao userDao;

    public RegisterHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
        userDao.addUser(user);
    }

    public void deregisterUser(User user) throws UserDoesNotExistsException {
        userDao.removeUser(user);
    }
}

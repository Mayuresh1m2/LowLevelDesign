package covidtracker.dao;

import covidtracker.exception.UserAlreadyExistsException;
import covidtracker.exception.UserDoesNotExistsException;
import covidtracker.user.User;

public interface UserDao {
    void addUser(User user) throws UserAlreadyExistsException;
    void removeUser(User user) throws UserDoesNotExistsException;
    User getUser(String userId);

}

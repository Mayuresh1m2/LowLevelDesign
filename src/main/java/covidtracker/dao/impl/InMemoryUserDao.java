package covidtracker.dao.impl;

import covidtracker.dao.UserDao;
import covidtracker.exception.UserAlreadyExistsException;
import covidtracker.exception.UserDoesNotExistsException;
import covidtracker.user.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDao implements UserDao {
    Map<String, User> userMap;

    public InMemoryUserDao() {
        this.userMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        if (userMap.containsKey(user.getUserId())) {
            throw new UserAlreadyExistsException("User is already present in the system");
        }
        userMap.put(user.getUserId(), user);
    }

    @Override
    public void removeUser(User user) throws UserDoesNotExistsException {
        if (!userMap.containsKey(user.getUserId())) {
            throw new UserDoesNotExistsException("This user is not present in system");
        }
    }

    @Override
    public User getUser(String userId) {
        return userMap.get(userId);
    }
}

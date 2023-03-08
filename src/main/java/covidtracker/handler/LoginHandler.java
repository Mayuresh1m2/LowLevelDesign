package covidtracker.handler;

import covidtracker.dao.SessionTokenDao;
import covidtracker.dao.UserDao;
import covidtracker.exception.IncorrectPasswordException;
import covidtracker.exception.UserDoesNotExistsException;
import covidtracker.session.SessionConstants;
import covidtracker.session.SessionToken;
import covidtracker.session.SessionUtil;
import covidtracker.user.User;

public class LoginHandler {

    UserDao userDao;
    SessionTokenDao sessionTokenDao;

    public LoginHandler(UserDao userDao, SessionTokenDao sessionTokenDao) {
        this.userDao = userDao;
        this.sessionTokenDao = sessionTokenDao;
    }

    private static void checkIfPasswordIsCorrect(String password, User user) throws IncorrectPasswordException {
        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Please check your password");
        }
    }

    private static void checkIfUserExistsInTheSystem(String userId, User user) throws UserDoesNotExistsException {
        if (user == null) {
            throw new UserDoesNotExistsException(String.format("User Id - [%s] is not " +
                    "registered", userId));
        }
    }

    public SessionToken login(String userId, String password) throws UserDoesNotExistsException,
            IncorrectPasswordException {
        User user = userDao.getUser(userId);
        checkIfUserExistsInTheSystem(userId, user);
        checkIfPasswordIsCorrect(password, user);
        return getSessionToken(userId);
    }

    private SessionToken getSessionToken(String userId) {
        SessionToken token = null;
        if (sessionTokenDao.getSessionToken(userId) == null) {
            token = new SessionToken(SessionUtil.generateSessionTokenId(), userId,
                    System.currentTimeMillis() + SessionConstants.MAX_SESSION_DURATION);
            sessionTokenDao.addSessionToken(
                    token,
                    userId);
        }
        return (token != null) ? token : sessionTokenDao.getSessionToken(userId);
    }
}

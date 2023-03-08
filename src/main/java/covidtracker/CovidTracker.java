package covidtracker;

import covidtracker.exception.*;
import covidtracker.handler.AdminHandler;
import covidtracker.handler.LoginHandler;
import covidtracker.handler.RegisterHandler;
import covidtracker.handler.SelfAssesmentHandler;
import covidtracker.self.assesment.SelfAssesment;
import covidtracker.self.assesment.SelfAssesmentResult;
import covidtracker.session.SessionToken;
import covidtracker.user.User;
import covidtracker.zone.Zone;

public class CovidTracker {
    private final AdminHandler adminHandler;
    private final LoginHandler loginHandler;
    private final RegisterHandler registerHandler;
    private final SelfAssesmentHandler selfAssesmentHandler;

    public CovidTracker(AdminHandler adminHandler, LoginHandler loginHandler, RegisterHandler registerHandler, SelfAssesmentHandler selfAssesmentHandler) {
        this.adminHandler = adminHandler;
        this.loginHandler = loginHandler;
        this.registerHandler = registerHandler;
        this.selfAssesmentHandler = selfAssesmentHandler;
    }

    public void register(User user) throws UserAlreadyExistsException {
        System.out.println("Registered" + user);
        registerHandler.registerUser(user);
    }

    public SessionToken login(String userId, String password) throws IncorrectPasswordException,
            UserDoesNotExistsException {
        return loginHandler.login(userId, password);
    }

    public SelfAssesmentResult selfAsses(SessionToken sessionToken, SelfAssesment selfAssesment) throws ZoneDoesNotExistException {
        return selfAssesmentHandler.selfAsses(sessionToken.getUserId(), selfAssesment);
    }

    public void addZone(SessionToken sessionToken, Zone zone) throws NotAnAdminException {
        checkIfUserIsAdmin(sessionToken);
        adminHandler.addZone(zone);
    }

    public void markCovidZone(SessionToken sessionToken, String zoneId) throws NotAnAdminException,
            ZoneDoesNotExistException {
        checkIfUserIsAdmin(sessionToken);
        adminHandler.markCovidZone(zoneId);
    }

    public void getSelfAssesmentDataOfAUser(SessionToken sessionToken, String userId) throws NotAnAdminException {
        checkIfUserIsAdmin(sessionToken);
        adminHandler.getSelfAssesmentData(userId);
    }

    private void checkIfUserIsAdmin(SessionToken sessionToken) throws NotAnAdminException {
        if (!adminHandler.isAdmin(sessionToken.getUserId()))
            throw new NotAnAdminException(String.format("User - %s is not an admin ",
                    sessionToken.getUserId()));
    }
}

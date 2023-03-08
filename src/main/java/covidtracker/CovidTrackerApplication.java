package covidtracker;

import covidtracker.dao.SelfAssesmentDao;
import covidtracker.dao.SessionTokenDao;
import covidtracker.dao.UserDao;
import covidtracker.dao.ZonesDao;
import covidtracker.dao.impl.InMemorySelfAssesmentDao;
import covidtracker.dao.impl.InMemorySessionTokenDao;
import covidtracker.dao.impl.InMemoryUserDao;
import covidtracker.dao.impl.InMemoryZonesDao;
import covidtracker.exception.*;
import covidtracker.handler.AdminHandler;
import covidtracker.handler.LoginHandler;
import covidtracker.handler.RegisterHandler;
import covidtracker.handler.SelfAssesmentHandler;
import covidtracker.self.assesment.SelfAssesment;
import covidtracker.session.SessionToken;
import covidtracker.user.User;
import covidtracker.zone.Zone;

import java.util.HashSet;
import java.util.Set;

public class CovidTrackerApplication {

    private static final InMemoryUserDao inMemoryUserDao = new InMemoryUserDao();
    private static final InMemorySessionTokenDao inMemorySessionTokenDao = new InMemorySessionTokenDao();
    private static final InMemorySelfAssesmentDao inMemorySelfAssesmentDao = new InMemorySelfAssesmentDao();
    private static final InMemoryZonesDao inMemoryZonesDao = new InMemoryZonesDao();

    public static void main(String[] args) throws UserAlreadyExistsException, IncorrectPasswordException, UserDoesNotExistsException, NotAnAdminException, ZoneDoesNotExistException {
        AdminHandler adminHandler = new AdminHandler(provideZonesDao(),
                provideSelfAssesmentDao(),provideUserDao());
        LoginHandler loginHandler = new LoginHandler(provideUserDao(),
                provideSessionTokenDao());

        RegisterHandler registerHandler = new RegisterHandler(provideUserDao());

        SelfAssesmentHandler selfAssesmentHandler =
                new SelfAssesmentHandler(provideSelfAssesmentDao(), provideZonesDao());
        CovidTracker covidTracker = new CovidTracker(adminHandler, loginHandler,
                registerHandler, selfAssesmentHandler);
        User regularUser1 =
                User.builder().userId("ru1").password("pass1").isAdmin(false).build();
        User regularUser2 =
                User.builder().userId("ru2").password("pass2").isAdmin(false).build();
        User adminUser1 =
                User.builder().userId("au1").password("pass3").isAdmin(true).build();
        System.out.println("-------------------------------------------------------------");
        covidTracker.register(regularUser1);
        covidTracker.register(regularUser2);
        covidTracker.register(adminUser1);
        SessionToken token1 = covidTracker.login(regularUser1.getUserId(), "pass1");
        SessionToken token3 = covidTracker.login(adminUser1.getUserId(), "pass3");
        Zone[] zones = new Zone[10];
        for (int i = 0; i < zones.length; i++) {
            zones[i] = Zone.builder().zoneId("z" + i).zoneName("z" + i).zonePinCode("pc" + i).build();
            covidTracker.addZone(token3,zones[i]);
        }
        covidTracker.markCovidZone(token3, "z1");
        Set<Zone> user1Visited = new HashSet<>();
        user1Visited.add(zones[0]);
        System.out.println(covidTracker.selfAsses(token1,
                SelfAssesment.builder().covidPatientContactStatus(true).vaccinationStatus(false)
                        .visitedZones(user1Visited).build()));
        System.out.println(covidTracker.selfAsses(token1,
                SelfAssesment.builder().covidPatientContactStatus(true).vaccinationStatus(true)
                        .visitedZones(user1Visited).build()));

        user1Visited.add(zones[1]);
        System.out.println(covidTracker.selfAsses(token1,
                SelfAssesment.builder().covidPatientContactStatus(true).vaccinationStatus(false)
                        .visitedZones(user1Visited).build()));
    }

    static UserDao provideUserDao() {
        return inMemoryUserDao;
    }

    static SessionTokenDao provideSessionTokenDao() {

        return inMemorySessionTokenDao;
    }

    static SelfAssesmentDao provideSelfAssesmentDao() {

        return inMemorySelfAssesmentDao;
    }

    static ZonesDao provideZonesDao() {
        return inMemoryZonesDao;
    }
}

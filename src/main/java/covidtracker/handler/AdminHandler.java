package covidtracker.handler;

import covidtracker.zone.Zone;
import covidtracker.dao.SelfAssesmentDao;
import covidtracker.dao.UserDao;
import covidtracker.dao.ZonesDao;
import covidtracker.exception.ZoneDoesNotExistException;
import covidtracker.self.assesment.SelfAssesment;

import java.util.List;

public class AdminHandler {
    ZonesDao zonesDao;
    SelfAssesmentDao selfAssesmentDao;
    UserDao userDao;

    public AdminHandler(ZonesDao zonesDao, SelfAssesmentDao selfAssesmentDao, UserDao userDao) {
        this.zonesDao = zonesDao;
        this.selfAssesmentDao = selfAssesmentDao;
        this.userDao = userDao;
    }


    public void addZone(Zone zone) {
        zonesDao.addZone(zone);
    }

    public void markCovidZone(String zoneId) throws ZoneDoesNotExistException {
        zonesDao.addCovidZone(zoneId);
    }

    public List<SelfAssesment> getSelfAssesmentData(String userId) {
        return selfAssesmentDao.getSelfAssesments(userId);
    }

    public boolean isAdmin(String userId) {
        return userDao.getUser(userId).isAdmin();
    }


}

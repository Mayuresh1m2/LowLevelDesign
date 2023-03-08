package covidtracker.handler;

import covidtracker.dao.SelfAssesmentDao;
import covidtracker.dao.ZonesDao;
import covidtracker.exception.ZoneDoesNotExistException;
import covidtracker.self.assesment.SelfAsses;
import covidtracker.self.assesment.SelfAssesment;
import covidtracker.self.assesment.SelfAssesmentResult;

public class SelfAssesmentHandler {
    SelfAssesmentDao selfAssesmentDao;
    ZonesDao zonesDao;
    SelfAsses selfAsses;

    public SelfAssesmentHandler(SelfAssesmentDao selfAssesmentDao, ZonesDao zonesDao) {
        this.selfAssesmentDao = selfAssesmentDao;
        this.zonesDao = zonesDao;
        selfAsses = new SelfAsses(zonesDao);
    }


    public SelfAssesmentResult selfAsses(String userId, SelfAssesment selfAssesment) throws ZoneDoesNotExistException {
        selfAssesmentDao.addSelfAssesment(userId, selfAssesment);
        return selfAsses.getSelfAssesmentResult(selfAssesment);

    }
}

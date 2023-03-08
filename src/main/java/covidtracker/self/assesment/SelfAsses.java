package covidtracker.self.assesment;

import covidtracker.zone.Zone;
import covidtracker.dao.ZonesDao;
import covidtracker.exception.ZoneDoesNotExistException;

import java.util.Set;

public final class SelfAsses {
    ZonesDao zonesDao;

    public SelfAsses(ZonesDao zonesDao) {
        this.zonesDao = zonesDao;
    }

    public SelfAssesmentResult getSelfAssesmentResult(SelfAssesment selfAssesment) throws ZoneDoesNotExistException {
        boolean vaccinated = selfAssesment.vaccinationStatus;
        boolean covidPatientContactStatus = selfAssesment.covidPatientContactStatus;
        Set<Zone> visitedZones = selfAssesment.getVisitedZones();
        boolean zoneStatus = false;
        for (Zone zone : visitedZones) {
            if (zonesDao.isCovidZone(zone.getZoneId())) {
                zoneStatus = true;
                break;
            }
        }
        if (!vaccinated && covidPatientContactStatus && zoneStatus) {
            return SelfAssesmentResult.HIGH_PROBABILITY_OF_COVID;
        }
        if (covidPatientContactStatus && zoneStatus) {
            return SelfAssesmentResult.MEDIUM_PROBABILITY_OF_COVID;
        }
        return SelfAssesmentResult.LOW_PROBABILITY_OF_COVID;


    }
}

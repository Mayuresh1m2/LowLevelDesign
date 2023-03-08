package covidtracker.dao;

import covidtracker.zone.Zone;
import covidtracker.exception.NotACovidZoneException;
import covidtracker.exception.ZoneDoesNotExistException;

public interface ZonesDao {
    void addZone(Zone zone);
    void removeZone(Zone zone);
    void addCovidZone(String zoneId) throws ZoneDoesNotExistException;
    void removeCovidZone(String zoneId) throws ZoneDoesNotExistException, NotACovidZoneException;
    boolean isCovidZone(String zoneId) throws ZoneDoesNotExistException;
}

package covidtracker.dao.impl;

import covidtracker.zone.Zone;
import covidtracker.dao.ZonesDao;
import covidtracker.exception.NotACovidZoneException;
import covidtracker.exception.ZoneDoesNotExistException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryZonesDao implements ZonesDao {
    Map<String, Zone> zones;

    public InMemoryZonesDao() {
        this.zones = new HashMap<>();
    }


    @Override
    public void addZone(Zone zone) {
        zones.put(zone.getZoneId(), zone);
    }

    @Override
    public void removeZone(Zone zone) {
        zones.remove(zone.getZoneId());
    }

    @Override
    public void addCovidZone(String zoneId) throws ZoneDoesNotExistException {
        checkIfZoneExists(zoneId);
        Zone zone = zones.get(zoneId);
        zone.setCovidZone(true);
    }

    @Override
    public void removeCovidZone(String zoneId) throws ZoneDoesNotExistException, NotACovidZoneException {
        checkIfZoneExists(zoneId);
        Zone zone = zones.get(zoneId);
        if (!zone.isCovidZone()) {
            throw new NotACovidZoneException(String.format("Zone with ID - %s is not a covid" +
                    " zone", zone.getZoneId()));

        }
        zone.setCovidZone(false);
    }

    private void checkIfZoneExists(String zoneId) throws ZoneDoesNotExistException {
        if (!zones.containsKey(zoneId)) {
            throw new ZoneDoesNotExistException(String.format("Zone with ID - %s does not " +
                    "exist", zoneId));
        }
    }

    @Override
    public boolean isCovidZone(String zoneId) throws ZoneDoesNotExistException {
        checkIfZoneExists(zoneId);
        return zones.get(zoneId).isCovidZone();
    }
}

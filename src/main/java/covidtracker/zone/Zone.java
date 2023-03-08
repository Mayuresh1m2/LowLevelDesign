package covidtracker.zone;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Zone {
    @Getter String zoneId;
    @Setter @Getter boolean isCovidZone;
    String zoneName;
    String zonePinCode;
}

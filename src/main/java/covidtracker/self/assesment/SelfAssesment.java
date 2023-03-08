package covidtracker.self.assesment;

import covidtracker.zone.Zone;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class SelfAssesment {

    boolean vaccinationStatus;
    boolean covidPatientContactStatus;
    Long selfAssesmentDate;
    Set<Zone> visitedZones;

    @Override
    public String toString() {
        return "SelfAssesment{" +
                "vaccinationStatus=" + vaccinationStatus +
                ", covidPatientContactStatus=" + covidPatientContactStatus +
                ", selfAssesmentDate=" + selfAssesmentDate +
                ", visitedZones=" + visitedZones +
                '}';
    }
}

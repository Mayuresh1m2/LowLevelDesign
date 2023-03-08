package covidtracker.exception;

public class NotACovidZoneException extends Throwable {
    public NotACovidZoneException(String s) {
        System.out.println(s);
    }
}

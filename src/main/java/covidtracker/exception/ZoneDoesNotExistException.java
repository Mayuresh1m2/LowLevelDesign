package covidtracker.exception;

public class ZoneDoesNotExistException extends Throwable {
    public ZoneDoesNotExistException(String message) {
        System.out.println(message);
    }
}

package covidtracker.exception;

public class NotAnAdminException extends Throwable {
    public NotAnAdminException(String format) {
        System.out.println(format);
    }
}

package covidtracker.exception;

public class UserDoesNotExistsException extends Throwable {
    public UserDoesNotExistsException(String m) {
        System.out.println(m);
    }
}

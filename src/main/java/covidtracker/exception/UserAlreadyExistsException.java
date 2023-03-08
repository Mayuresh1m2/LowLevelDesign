package covidtracker.exception;

public class UserAlreadyExistsException extends Throwable {
    public UserAlreadyExistsException(String message) {
        System.out.println(message);
    }
}

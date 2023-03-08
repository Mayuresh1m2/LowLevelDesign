package covidtracker.exception;

public class IncorrectPasswordException extends Throwable {
    public IncorrectPasswordException(String pleaseCheckYourPassword) {
        System.out.println(pleaseCheckYourPassword);
    }
}

package digitalwallet.exception;

public class InsufficientFundException extends Throwable {
    public InsufficientFundException(String message) {
        System.out.println(message);
    }
}

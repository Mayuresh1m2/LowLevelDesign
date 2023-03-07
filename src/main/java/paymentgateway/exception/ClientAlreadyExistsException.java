package paymentgateway.exception;

public class ClientAlreadyExistsException extends Throwable {
    public ClientAlreadyExistsException(String message) {
        System.out.println(message);
    }
}

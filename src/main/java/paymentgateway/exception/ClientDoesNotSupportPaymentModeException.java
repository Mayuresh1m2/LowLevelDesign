package paymentgateway.exception;

public class ClientDoesNotSupportPaymentModeException extends Throwable {
    public ClientDoesNotSupportPaymentModeException(String message) {
        System.out.println(message);
    }
}

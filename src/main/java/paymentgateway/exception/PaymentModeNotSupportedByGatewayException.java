package paymentgateway.exception;

public class PaymentModeNotSupportedByGatewayException extends Throwable {
    public PaymentModeNotSupportedByGatewayException(String message) {
        System.out.println(message);
    }
}

package paymentgateway.client;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;


@Getter
public class Client {
    String clientId;
    Set<PaymentMode> supportedPaymentModes;

    public Client(@NonNull String clientId) {
        this.clientId = clientId;
        this.supportedPaymentModes = new HashSet<>();
    }

    public void addPaymentMode(PaymentMode paymentMode) {
        supportedPaymentModes.add(paymentMode);
    }

    public void removePaymentMode(PaymentMode paymentMode) {
        supportedPaymentModes.remove(paymentMode);
    }
}

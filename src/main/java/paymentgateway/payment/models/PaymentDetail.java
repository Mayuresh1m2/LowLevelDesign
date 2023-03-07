package paymentgateway.payment.models;

import lombok.Getter;
import paymentgateway.client.PaymentMode;

import java.math.BigDecimal;

public abstract class PaymentDetail {
    @Getter
    PaymentMode paymentMode;
    BigDecimal amount;

    public PaymentDetail(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
}

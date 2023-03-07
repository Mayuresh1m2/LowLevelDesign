package paymentgateway.payment;

import paymentgateway.payment.models.PaymentDetail;

import java.math.BigDecimal;

public interface PaymentHandler {
    void makePayment(PaymentDetail paymentDetail);
}

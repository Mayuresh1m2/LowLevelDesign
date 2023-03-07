package paymentgateway.router;

import paymentgateway.payment.models.PaymentDetail;

import java.math.BigDecimal;

public interface TxRouter {
    void makePayment(PaymentDetail paymentDetail);
    TxRouterType getTxRouterType();
}

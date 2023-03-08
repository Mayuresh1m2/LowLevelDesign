package paymentgateway.payment.handler;

import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.TxRouter;

public abstract class PaymentHandler {
    TxRouter txRouter;

    public PaymentHandler(TxRouter txRouter) {
        this.txRouter = txRouter;
    }

    public abstract void makePayment(PaymentDetail paymentDetail);
}

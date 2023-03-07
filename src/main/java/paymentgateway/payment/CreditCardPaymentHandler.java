package paymentgateway.payment;

import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.TxRouter;

public class CreditCardPaymentHandler implements PaymentHandler {
    TxRouter txRouter;

    public CreditCardPaymentHandler(TxRouter txRouter) {
        this.txRouter = txRouter;
    }

    @Override
    public void makePayment(PaymentDetail paymentDetail) {
        txRouter.makePayment(paymentDetail);
    }
}

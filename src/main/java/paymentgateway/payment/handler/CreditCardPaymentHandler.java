package paymentgateway.payment.handler;

import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.TxRouter;

public class CreditCardPaymentHandler extends PaymentHandler {


    public CreditCardPaymentHandler(TxRouter txRouter) {
        super(txRouter);
    }

    @Override
    public void makePayment(PaymentDetail paymentDetail) {
        txRouter.makePayment(paymentDetail);
    }
}

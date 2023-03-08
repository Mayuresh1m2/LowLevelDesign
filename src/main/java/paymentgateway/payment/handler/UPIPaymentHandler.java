package paymentgateway.payment.handler;

import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.TxRouter;

public class UPIPaymentHandler extends PaymentHandler {
    public UPIPaymentHandler(TxRouter txRouter) {
        super(txRouter);
    }

    @Override
    public void makePayment(PaymentDetail paymentDetail) {
        txRouter.makePayment(paymentDetail);
    }
}

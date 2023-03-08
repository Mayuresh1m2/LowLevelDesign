package paymentgateway.payment.handler;

import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.TxRouter;

public class NetBankingPaymentHandler extends PaymentHandler {
    public NetBankingPaymentHandler(TxRouter txRouter) {
        super(txRouter);
    }

    @Override
    public void makePayment(PaymentDetail paymentDetail) {
        txRouter.makePayment(paymentDetail);
    }
}

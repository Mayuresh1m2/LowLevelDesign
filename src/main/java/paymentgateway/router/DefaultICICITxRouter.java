package paymentgateway.router;

import paymentgateway.banks.BankApi;
import paymentgateway.banks.ICICIBankApi;
import paymentgateway.payment.PaymentUtil;
import paymentgateway.payment.models.PaymentDetail;

public class DefaultICICITxRouter implements TxRouter {
    BankApi iciciBankApi;

    public DefaultICICITxRouter() {
        this.iciciBankApi = new ICICIBankApi();
    }

    @Override
    public void makePayment(PaymentDetail paymentDetail) {
        PaymentUtil.makePayment(paymentDetail, iciciBankApi);
    }

    @Override
    public TxRouterType getTxRouterType() {
        return TxRouterType.DEFAULT_ICICI;
    }
}

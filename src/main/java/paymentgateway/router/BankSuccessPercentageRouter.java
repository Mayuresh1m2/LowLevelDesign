package paymentgateway.router;

import paymentgateway.banks.BankApi;
import paymentgateway.banks.HDFCBankApi;
import paymentgateway.banks.ICICIBankApi;
import paymentgateway.payment.PaymentUtil;
import paymentgateway.payment.models.PaymentDetail;

public class BankSuccessPercentageRouter implements TxRouter {
    BankApi iciciBankApi;
    BankApi hdfcBankApi;

    public BankSuccessPercentageRouter() {
        hdfcBankApi = new HDFCBankApi();
        iciciBankApi = new ICICIBankApi();
    }

    @Override
    public void makePayment(PaymentDetail paymentDetail) {
        int hdfcSuccessPercentage = hdfcBankApi.getSuccessPercentage();
        int iciciSuccessPercentage = iciciBankApi.getSuccessPercentage();
        BankApi bankToUseBasedOnSuccessPercentage = (hdfcSuccessPercentage > iciciSuccessPercentage) ? hdfcBankApi : iciciBankApi;
        System.out.printf("HDFC Success  - %d  ICICI Bank success - %d%n", hdfcSuccessPercentage, iciciSuccessPercentage);
        PaymentUtil.makePayment(paymentDetail, bankToUseBasedOnSuccessPercentage);

    }

    @Override
    public TxRouterType getTxRouterType() {
        return TxRouterType.BANK_SUCCESS_PERCENTAGE;
    }
}

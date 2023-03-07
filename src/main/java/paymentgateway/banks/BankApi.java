package paymentgateway.banks;

import paymentgateway.payment.models.CreditCardPaymentDetail;
import paymentgateway.payment.models.NetBankingPaymentDetail;
import paymentgateway.payment.models.UPIPaymentDetail;

public interface BankApi {
    void makeCreditCardPayment(CreditCardPaymentDetail creditCardPaymentDetail);

    void makeUPIPayment(UPIPaymentDetail upiPaymentDetail);

    void makeNetBankingPayment(NetBankingPaymentDetail netBankingPaymentDetail);
    int getSuccessPercentage();
}

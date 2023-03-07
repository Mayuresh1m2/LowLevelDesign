package paymentgateway.payment;

import paymentgateway.banks.BankApi;
import paymentgateway.payment.models.CreditCardPaymentDetail;
import paymentgateway.payment.models.NetBankingPaymentDetail;
import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.payment.models.UPIPaymentDetail;

public class PaymentUtil {
    public static void makePayment(PaymentDetail paymentDetail, BankApi bankApi) {
        if (paymentDetail instanceof CreditCardPaymentDetail) {
            bankApi.makeCreditCardPayment((CreditCardPaymentDetail) paymentDetail);
        } else if (paymentDetail instanceof NetBankingPaymentDetail) {
            bankApi.makeNetBankingPayment((NetBankingPaymentDetail) paymentDetail);
        } else if (paymentDetail instanceof UPIPaymentDetail) {
            bankApi.makeUPIPayment((UPIPaymentDetail) paymentDetail);
        }
    }
}

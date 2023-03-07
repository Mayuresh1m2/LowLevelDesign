package paymentgateway.banks;

import paymentgateway.payment.models.CreditCardPaymentDetail;
import paymentgateway.payment.models.NetBankingPaymentDetail;
import paymentgateway.payment.models.UPIPaymentDetail;

import java.util.concurrent.ThreadLocalRandom;

public class HDFCBankApi implements BankApi{
    @Override
    public void makeCreditCardPayment(CreditCardPaymentDetail creditCardPaymentDetail) {
        System.out.println("Credit card payment by HDFC");
    }

    @Override
    public void makeUPIPayment(UPIPaymentDetail upiPaymentDetail) {
        System.out.println("UPI payment by HDFC");
    }

    @Override
    public void makeNetBankingPayment(NetBankingPaymentDetail netBankingPaymentDetail) {
        System.out.println("Net Banking payment by HDFC");
    }

    @Override
    public int getSuccessPercentage() {
        return ThreadLocalRandom.current().nextInt(0,100);
    }
}

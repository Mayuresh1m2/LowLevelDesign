package paymentgateway.payment.models;

import paymentgateway.client.PaymentMode;

public class NetBankingPaymentDetail extends PaymentDetail {
    String userName, password;

    public NetBankingPaymentDetail(String userName, String password) {
        super(PaymentMode.NET_BANKING);
        this.userName = userName;
        this.password = password;
    }
}

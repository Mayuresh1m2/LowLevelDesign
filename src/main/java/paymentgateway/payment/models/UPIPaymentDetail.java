package paymentgateway.payment.models;

import paymentgateway.client.PaymentMode;

public class UPIPaymentDetail extends PaymentDetail {
    String vpa;


    public UPIPaymentDetail(String vpa) {
        super(PaymentMode.UPI);
        this.vpa = vpa;
    }
}

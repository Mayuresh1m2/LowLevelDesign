package paymentgateway.payment.models;

import paymentgateway.client.PaymentMode;

public class CreditCardPaymentDetail extends PaymentDetail {
    String cardNumber, expiry, name;

    public CreditCardPaymentDetail(String cardNumber, String expiry, String name) {
        super(PaymentMode.CREDIT_CARD);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.name = name;
    }
}

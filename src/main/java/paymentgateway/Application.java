package paymentgateway;

import paymentgateway.client.PaymentMode;
import paymentgateway.exception.PaymentModeNotSupportedByGatewayException;
import paymentgateway.handler.ClientHandler;
import paymentgateway.handler.ClientPaymentHandler;
import paymentgateway.payment.models.CreditCardPaymentDetail;
import paymentgateway.payment.models.NetBankingPaymentDetail;
import paymentgateway.payment.models.UPIPaymentDetail;

public class Application {
    public static void main(String[] args) throws PaymentModeNotSupportedByGatewayException {
        PaymentGateway paymentGateway = new PaymentGateway(new ClientHandler(), new ClientPaymentHandler());
        String client1 = paymentGateway.addClient("Client1");
        String client2 = paymentGateway.addClient("Client2");

        System.out.println("----------List of supported payment modes for PG-------------");
        paymentGateway.listSupportedPaymentModes();
        System.out.println("----------List of supported payment modes for client 1-------------");
        paymentGateway.listSupportedPaymentModes(client1);
        System.out.println("----------List of supported payment modes for client 2-------------");
        paymentGateway.listSupportedPaymentModes(client2);
        System.out.println("----------Client 1 status in payment gateway-------------");
        System.out.println(paymentGateway.hasClient(client1));
        System.out.println("-------------------Credit card payment--------------------------------------");
        paymentGateway.addSupportForPaymentMode(PaymentMode.CREDIT_CARD);
        paymentGateway.addSupportForPaymentMode(client1, PaymentMode.CREDIT_CARD);
        paymentGateway.makePayment(client1, new CreditCardPaymentDetail("CARD-NUMBER", "EXPIRY", "NAME"));
        System.out.println("-------------------UPI payment--------------------------------------");
        paymentGateway.addSupportForPaymentMode(PaymentMode.UPI);
        paymentGateway.addSupportForPaymentMode(client1,PaymentMode.UPI);
        paymentGateway.makePayment(client1, new UPIPaymentDetail("VPA@okaxis"));
        System.out.println("-------------------Net Banking payment--------------------------------------");
        paymentGateway.makePayment(client1, new NetBankingPaymentDetail("User1", "password"));


    }
}

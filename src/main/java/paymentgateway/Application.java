package paymentgateway;

import paymentgateway.client.PaymentMode;
import paymentgateway.exception.ClientDoesNotSupportPaymentModeException;
import paymentgateway.exception.PaymentModeNotSupportedByGatewayException;
import paymentgateway.handler.ClientHandler;
import paymentgateway.handler.ClientPaymentHandler;
import paymentgateway.payment.models.CreditCardPaymentDetail;
import paymentgateway.payment.models.NetBankingPaymentDetail;
import paymentgateway.payment.models.UPIPaymentDetail;

public class Application {
    public static void main(String[] args) throws PaymentModeNotSupportedByGatewayException, ClientDoesNotSupportPaymentModeException {
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
        paymentGateway.addSupportForPaymentMode(client1, PaymentMode.UPI);
        paymentGateway.makePayment(client1, new UPIPaymentDetail("VPA@okaxis"));
        System.out.println("-------------------Net Banking payment--------------------------------------");
        try {
            paymentGateway.makePayment(client1, new NetBankingPaymentDetail("User1", "password"));
        } catch (ClientDoesNotSupportPaymentModeException exception) {
            System.out.println("Expecting exception - Ignoring");
        }
        paymentGateway.addSupportForPaymentMode(PaymentMode.NET_BANKING);
        paymentGateway.addSupportForPaymentMode(client1, PaymentMode.NET_BANKING);
        paymentGateway.makePayment(client1, new NetBankingPaymentDetail("User1", "password"));
        System.out.println("----------------------Removing payment mode-----------------------------------");
        paymentGateway.removePaymentMode(PaymentMode.NET_BANKING);
        paymentGateway.removePaymentMode(client1,PaymentMode.UPI);

        System.out.println("----------------------Removing client-----------------------------------");
        paymentGateway.removeClient(client1);


    }
}

package paymentgateway;

import paymentgateway.client.PaymentMode;
import paymentgateway.exception.ClientDoesNotSupportPaymentModeException;
import paymentgateway.exception.PaymentModeNotSupportedByGatewayException;
import paymentgateway.handler.ClientHandler;
import paymentgateway.handler.ClientPaymentHandler;
import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.TxRouterType;

public class PaymentGateway {
    ClientHandler clientHandler;
    ClientPaymentHandler clientPaymentHandler;

    public PaymentGateway(ClientHandler clientHandler, ClientPaymentHandler clientPaymentHandler) {
        this.clientHandler = clientHandler;
        this.clientPaymentHandler = clientPaymentHandler;
    }

    public String addClient(String id) {
        return clientHandler.addClient(id);
    }

    public void removeClient(String id) {
        clientHandler.removeClient(id);
    }

    public boolean hasClient(String id) {
        return clientHandler.hasClient(id);
    }

    public void listSupportedPaymentModes() {
        for (PaymentMode paymentMode : clientPaymentHandler.getSupportedPaymentModes()) {
            System.out.println(paymentMode);
        }
    }

    public void listSupportedPaymentModes(String clientId) {
        clientHandler.listSupportedPaymentModes(clientId);
    }

    public void addSupportForPaymentMode(PaymentMode paymentMode) {
        clientPaymentHandler.addPaymentMode(paymentMode);
    }

    public void addSupportForPaymentMode(String clientID, PaymentMode paymentMode) throws PaymentModeNotSupportedByGatewayException {
        if (!clientPaymentHandler.getSupportedPaymentModes().contains(paymentMode)) {
            throw new PaymentModeNotSupportedByGatewayException("This payment mode is not supported in Payment Gateway");
        }
        clientHandler.addPaymentMode(clientID, paymentMode);


    }

    public void removePaymentMode(PaymentMode paymentMode) {
        clientPaymentHandler.removePaymentMode(paymentMode);
        clientHandler.removePaymentMode(paymentMode);
    }

    public void removePaymentMode(String clientId, PaymentMode paymentMode) {
        clientHandler.removePaymentMode(clientId, paymentMode);
    }

    public void makePayment(String clientId, PaymentDetail paymentDetail) throws ClientDoesNotSupportPaymentModeException {
        if (!clientHandler.supportsPaymentMode(clientId, paymentDetail.getPaymentMode())) {
            throw new ClientDoesNotSupportPaymentModeException(
                    String.format("Payment mode %s not supported by client with id %s", paymentDetail.getPaymentMode(), clientId));
        }
        clientPaymentHandler.makePayment(paymentDetail, TxRouterType.BANK_SUCCESS_PERCENTAGE);
    }
}

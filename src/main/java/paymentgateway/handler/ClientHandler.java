package paymentgateway.handler;

import paymentgateway.client.Client;
import paymentgateway.client.PaymentMode;

import java.util.HashMap;
import java.util.Map;

public class ClientHandler {
    Map<String, Client> clients;

    public ClientHandler() {
        this.clients = new HashMap<>();
    }

    public String addClient(String clientId) {
        Client client = new Client(clientId);
        clients.put(clientId, client);
        return clientId;
    }

    public void removeClient(String clientId) {
        clients.remove(clientId);
    }

    public boolean hasClient(String clientId) {
        return clients.containsKey(clientId);
    }

    public void listSupportedPaymentModes(String clientId) {
        for (PaymentMode paymentMode : clients.get(clientId).getSupportedPaymentModes()) {
            System.out.println(paymentMode);
        }
    }

    public void addPaymentMode(String clientID, PaymentMode paymentMode) {
        clients.get(clientID).addPaymentMode(paymentMode);
    }

    public void removePaymentMode(String clientId, PaymentMode paymentMode) {
        clients.get(clientId).removePaymentMode(paymentMode);
    }

    public void removePaymentMode(PaymentMode paymentMode) {
        for (Client client : clients.values()) {
            if (client.getSupportedPaymentModes().contains(paymentMode)) {
                client.removePaymentMode(paymentMode);
            }
        }
    }
}

package paymentgateway.handler;

import lombok.Getter;
import paymentgateway.client.PaymentMode;
import paymentgateway.payment.PaymentHandler;
import paymentgateway.payment.PaymentHandlerFactory;
import paymentgateway.payment.models.PaymentDetail;
import paymentgateway.router.BankSuccessPercentageRouter;
import paymentgateway.router.TxRouter;
import paymentgateway.router.TxRouterType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientPaymentHandler {

    @Getter
    Set<PaymentMode> supportedPaymentModes;
    Set<TxRouter> txRouters;
    PaymentHandlerFactory paymentHandlerFactory;
    Map<PaymentMode, PaymentHandler> paymentHandlers;

    public ClientPaymentHandler() {
        supportedPaymentModes = new HashSet<>();
        txRouters = new HashSet<>();
        paymentHandlerFactory = new PaymentHandlerFactory();
        paymentHandlers = new HashMap<>();
    }

    public void addPaymentMode(PaymentMode paymentMode) {
        supportedPaymentModes.add(paymentMode);
    }

    public void removePaymentMode(PaymentMode paymentMode) {
        supportedPaymentModes.remove(paymentMode);
    }

    public void makePayment(PaymentDetail paymentDetail, TxRouterType txRouterType) {
        paymentHandlerFactory.getPaymentHandler(paymentDetail.getPaymentMode(), txRouterType).makePayment(paymentDetail);
    }


    public TxRouter chooseTxRouter() {
        System.out.println("Choosing Tx Router");
        return new BankSuccessPercentageRouter();
    }
}

package paymentgateway.payment.handler;

import paymentgateway.client.PaymentMode;
import paymentgateway.router.TxRouterFactory;
import paymentgateway.router.TxRouterType;

import java.util.HashMap;
import java.util.Map;

public class PaymentHandlerFactory {
    Map<PaymentMode, Map<TxRouterType, PaymentHandler>> paymentHandlerMap;
    TxRouterFactory txRouterFactory;

    public PaymentHandlerFactory() {
        paymentHandlerMap = new HashMap<>();
        txRouterFactory = new TxRouterFactory();
        populatePaymentHandlersMap();
    }

    private void populatePaymentHandlersMap() {
        addCreditCardHandlers();
        addUPIHandlers();
        addNetBankingHandler();
    }

    private void addNetBankingHandler() {
        Map<TxRouterType, PaymentHandler> txRouterTypePaymentHandlerMap = paymentHandlerMap.getOrDefault(PaymentMode.NET_BANKING, new HashMap<>());
        txRouterTypePaymentHandlerMap.put(TxRouterType.DEFAULT_ICICI, new NetBankingPaymentHandler(txRouterFactory.getTxRouter(TxRouterType.DEFAULT_ICICI)));
        txRouterTypePaymentHandlerMap.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new NetBankingPaymentHandler(txRouterFactory.getTxRouter(TxRouterType.BANK_SUCCESS_PERCENTAGE)));
        paymentHandlerMap.put(PaymentMode.NET_BANKING, txRouterTypePaymentHandlerMap);

    }

    private void addUPIHandlers() {
        Map<TxRouterType, PaymentHandler> txRouterTypePaymentHandlerMap = paymentHandlerMap.getOrDefault(PaymentMode.UPI, new HashMap<>());
        txRouterTypePaymentHandlerMap.put(TxRouterType.DEFAULT_ICICI, new UPIPaymentHandler(txRouterFactory.getTxRouter(TxRouterType.DEFAULT_ICICI)));
        txRouterTypePaymentHandlerMap.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new UPIPaymentHandler(txRouterFactory.getTxRouter(TxRouterType.BANK_SUCCESS_PERCENTAGE)));
        paymentHandlerMap.put(PaymentMode.UPI, txRouterTypePaymentHandlerMap);
    }

    private void addCreditCardHandlers() {
        Map<TxRouterType, PaymentHandler> txRouterTypePaymentHandlerMap = paymentHandlerMap.getOrDefault(PaymentMode.CREDIT_CARD, new HashMap<>());
        txRouterTypePaymentHandlerMap.put(TxRouterType.DEFAULT_ICICI, new CreditCardPaymentHandler(txRouterFactory.getTxRouter(TxRouterType.DEFAULT_ICICI)));
        txRouterTypePaymentHandlerMap.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new CreditCardPaymentHandler(txRouterFactory.getTxRouter(TxRouterType.BANK_SUCCESS_PERCENTAGE)));
        paymentHandlerMap.put(PaymentMode.CREDIT_CARD, txRouterTypePaymentHandlerMap);
    }


    public PaymentHandler getPaymentHandler(PaymentMode paymentMode, TxRouterType txRouterType) {
        return paymentHandlerMap.get(paymentMode).get(txRouterType);
    }
}

package paymentgateway.payment;

import paymentgateway.client.PaymentMode;
import paymentgateway.router.BankSuccessPercentageRouter;
import paymentgateway.router.DefaultICICITxRouter;
import paymentgateway.router.TxRouterType;

import java.util.HashMap;
import java.util.Map;

public class PaymentHandlerFactory {
    Map<PaymentMode, Map<TxRouterType, PaymentHandler>> paymentHandlerMap;

    public PaymentHandlerFactory() {
        paymentHandlerMap = new HashMap<>();
        populatePaymentHandlersMap();
    }

    private void populatePaymentHandlersMap() {
        addCreditCardHandlers();
        addUPIHandlers();
        addNetBankingHandler();
    }

    private void addNetBankingHandler() {
        Map<TxRouterType, PaymentHandler> txRouterTypePaymentHandlerMap = paymentHandlerMap.getOrDefault(PaymentMode.NET_BANKING, new HashMap<>());
        txRouterTypePaymentHandlerMap.put(TxRouterType.DEFAULT_ICICI, new CreditCardPaymentHandler(new DefaultICICITxRouter()));
        txRouterTypePaymentHandlerMap.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new CreditCardPaymentHandler(new BankSuccessPercentageRouter()));
        paymentHandlerMap.put(PaymentMode.NET_BANKING, txRouterTypePaymentHandlerMap);

    }

    private void addUPIHandlers() {
        Map<TxRouterType, PaymentHandler> txRouterTypePaymentHandlerMap = paymentHandlerMap.getOrDefault(PaymentMode.UPI, new HashMap<>());
        txRouterTypePaymentHandlerMap.put(TxRouterType.DEFAULT_ICICI, new CreditCardPaymentHandler(new DefaultICICITxRouter()));
        txRouterTypePaymentHandlerMap.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new CreditCardPaymentHandler(new BankSuccessPercentageRouter()));
        paymentHandlerMap.put(PaymentMode.UPI, txRouterTypePaymentHandlerMap);
    }

    private void addCreditCardHandlers() {
        Map<TxRouterType, PaymentHandler> txRouterTypePaymentHandlerMap = paymentHandlerMap.getOrDefault(PaymentMode.CREDIT_CARD, new HashMap<>());
        txRouterTypePaymentHandlerMap.put(TxRouterType.DEFAULT_ICICI, new CreditCardPaymentHandler(new DefaultICICITxRouter()));
        txRouterTypePaymentHandlerMap.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new CreditCardPaymentHandler(new BankSuccessPercentageRouter()));
        paymentHandlerMap.put(PaymentMode.CREDIT_CARD, txRouterTypePaymentHandlerMap);
    }


    public PaymentHandler getPaymentHandler(PaymentMode paymentMode, TxRouterType txRouterType) {
        return paymentHandlerMap.get(paymentMode).get(txRouterType);
    }
}

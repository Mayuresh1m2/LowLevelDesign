package paymentgateway.router;

import java.util.HashMap;
import java.util.Map;

public class TxRouterFactory {

    Map<TxRouterType, TxRouter> txRouters;

    public TxRouterFactory() {
        txRouters = new HashMap<>();
        txRouters.put(TxRouterType.DEFAULT_ICICI, new DefaultICICITxRouter());
        txRouters.put(TxRouterType.BANK_SUCCESS_PERCENTAGE, new BankSuccessPercentageRouter());
    }

    public TxRouter getTxRouter(TxRouterType txRouterType) {
        return txRouters.get(txRouterType);
    }
}

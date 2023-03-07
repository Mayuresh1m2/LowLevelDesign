package digitalwallet.dao;

import digitalwallet.transaction.Status;
import digitalwallet.transaction.Type;

public interface TransactionDao {

    void addTransaction(long amount, String fromId, String toId, long time, Status status, Type type);
    void printStatement(String userId);
}

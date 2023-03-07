package digitalwallet.dao.impl;

import digitalwallet.dao.TransactionDao;
import digitalwallet.transaction.Status;
import digitalwallet.transaction.Transaction;
import digitalwallet.transaction.Type;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryTransactionDao implements TransactionDao {
    Map<String, List<Transaction>> userTransactionMap;

    public InMemoryTransactionDao() {
        userTransactionMap = new HashMap<>();
    }


    @Override
    public void addTransaction(long amount, String fromId, String toId, long time, Status status, Type type) {
        List<Transaction> fromTransactionList = userTransactionMap.getOrDefault(fromId, new LinkedList<>());
        fromTransactionList.add(Transaction.builder()
                .fromId(fromId)
                .toId(toId)
                .amountTransferred(amount)
                .time(time)
                .status(status)
                .type(type)
                .build());
        userTransactionMap.put(fromId, fromTransactionList);
        if (fromId.equals(toId)) return;
        List<Transaction> toTransactionList = userTransactionMap.getOrDefault(toId, new LinkedList<>());
        toTransactionList.add(Transaction.builder()
                .fromId(fromId)
                .toId(toId)
                .amountTransferred(-1 * amount)
                .time(time)
                .status(status)
                .type(type)
                .build());
        userTransactionMap.put(toId, toTransactionList);
    }

    @Override
    public void printStatement(String userId) {
        for (Transaction transaction : userTransactionMap.get(userId)) {
            System.out.println(transaction);
        }
    }
}

package keyvaluestore.store.impl;

import keyvaluestore.transaction.Transaction;
import keyvaluestore.store.KeyValueStore;

import java.util.HashMap;
import java.util.Map;

public class InMemoryKeyValueStore<K, V> implements KeyValueStore<K, V> {
    Map<K, V> inMemoryStore;
    Map<String, Transaction<K, V>> transactionStore;

    public InMemoryKeyValueStore() {
        inMemoryStore = new HashMap<>();
        transactionStore = new HashMap<>();
    }

    @Override
    public V get(K k) {
        return inMemoryStore.get(k);
    }

    @Override
    public void set(K key, V value) {
        inMemoryStore.put(key, value);
    }

    @Override
    public void delete(K key) {
        inMemoryStore.remove(key);
    }

    @Override
    public V get(K k, String transactionId) {
        return transactionStore.get(transactionId).getTransientInMemoryStore().get(k);
    }

    @Override
    public void set(K key, V value, String transactionId) {
        transactionStore.get(transactionId).getTransientInMemoryStore().put(key, value);
    }

    @Override
    public void delete(K key, String transactionId) {
        transactionStore.get(transactionId).getTransientInMemoryStore().remove(key);
    }

    @Override
    public void beginTransaction(String transactionId) {
        transactionStore.put(transactionId, new Transaction<K, V>(inMemoryStore));

    }

    @Override
    public void commit(String transactionId) {
        inMemoryStore = transactionStore.get(transactionId).getTransientInMemoryStore();
    }

    @Override
    public void rollback(String transactionId) {
        transactionStore.remove(transactionId);
    }


}
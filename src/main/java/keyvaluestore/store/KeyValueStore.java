package keyvaluestore.store;

public interface KeyValueStore<K,V> {
    V get(K k);
    void set(K key, V value);
    void delete(K key);
    void beginTransaction(String transactionID);
    V get(K k, String transactionId);
    void set(K key, V value, String transactionId);
    void delete(K key, String transactionId);
    void commit(String transactionId);
    void rollback(String transactionId);
}
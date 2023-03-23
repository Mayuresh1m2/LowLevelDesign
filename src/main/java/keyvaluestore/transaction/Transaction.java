package keyvaluestore.transaction;

import java.util.HashMap;
import java.util.Map;

public class Transaction<K, V> {
    public Map<K, V> getTransientInMemoryStore() {
        return transientInMemoryStore;
    }

    Map<K, V> transientInMemoryStore;

    public Transaction(Map<K, V> inMemoryStore) {
        transientInMemoryStore = new HashMap<>(inMemoryStore);
    }
}
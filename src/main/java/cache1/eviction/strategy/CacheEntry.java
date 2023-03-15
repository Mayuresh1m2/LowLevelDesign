package cache1.eviction.strategy;

import lombok.Getter;
import lombok.Setter;

public class CacheEntry<K, V> {
    @Getter
    K key;
    @Getter
    @Setter
    V value;

    public CacheEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

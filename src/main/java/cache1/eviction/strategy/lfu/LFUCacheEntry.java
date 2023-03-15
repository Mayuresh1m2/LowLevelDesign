package cache1.eviction.strategy.lfu;

import cache1.eviction.strategy.CacheEntry;
import lombok.Getter;

public class LFUCacheEntry<K, V> extends CacheEntry<K, V> {
    @Getter
    private int frequency;

    public LFUCacheEntry(K key, V value) {
        super(key, value);
        frequency = 1;
    }

    void incrementFrequency() {
        frequency++;
    }
}

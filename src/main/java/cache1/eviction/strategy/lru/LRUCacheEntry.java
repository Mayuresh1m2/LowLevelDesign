package cache1.eviction.strategy.lru;

import cache1.eviction.strategy.CacheEntry;
import lombok.Getter;
import lombok.Setter;

public class LRUCacheEntry<K, V> extends CacheEntry<K,V> {
    @Getter
    @Setter
    LRUCacheEntry<K, V> prev, next;

    public LRUCacheEntry(K key, V value) {
        super(key, value);
    }
}

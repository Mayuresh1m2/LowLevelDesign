package cache1.eviction.strategy;

import cache1.eviction.strategy.lfu.LFUCacheEntry;
import cache1.eviction.strategy.lru.LRUCacheEntry;

public class CacheEntryFactory<K, V> {
    public CacheEntry<K, V> getNewCacheEntry(EvictionStrategyType evictionStrategyType, K k, V v) {
        switch (evictionStrategyType) {
            case LFU -> {
                return new LFUCacheEntry<>(k, v);
            }
            case LRU -> {
                return new LRUCacheEntry<>(k, v);
            }
        }
        return null;
    }
}

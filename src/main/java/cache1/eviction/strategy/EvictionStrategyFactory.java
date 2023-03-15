package cache1.eviction.strategy;

import cache1.eviction.strategy.lfu.LFUEvictionStrategy;
import cache1.eviction.strategy.lru.LRUEvictionStrategy;

public class EvictionStrategyFactory<K, V> {

    public EvictionStrategy<K, V> getEvictionStrategy(EvictionStrategyType evictionStrategyType) {
        switch (evictionStrategyType) {
            case LRU -> {
                return new LRUEvictionStrategy<>();
            }
            case LFU -> {
                return new LFUEvictionStrategy<>();
            }
        }
        return null;

    }
}

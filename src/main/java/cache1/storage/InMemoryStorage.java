package cache1.storage;

import cache1.eviction.strategy.*;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage<K, V> implements Storage<K, V> {
    EvictionStrategyFactory<K, V> evictionStrategyFactory;
    EvictionStrategy<K, V> evictionStrategy;
    CacheEntryFactory<K, V> cacheEntryFactory;
    EvictionStrategyType evictionStrategyType;
    Map<K, CacheEntry<K, V>> kvMap;

    public InMemoryStorage(EvictionStrategyType evictionStrategyType) {
        this.evictionStrategyType = evictionStrategyType;
        this.evictionStrategyFactory = new EvictionStrategyFactory<>();
        evictionStrategy = evictionStrategyFactory.getEvictionStrategy(evictionStrategyType);

        cacheEntryFactory = new CacheEntryFactory<>();
        kvMap = new HashMap<>();
    }


    @Override
    public void put(K key, V value) {
        CacheEntry<K, V> val = kvMap.get(key);
        if (val != null) {
            val.setValue(value);
            get(key);
            return;
        }
        val = cacheEntryFactory.getNewCacheEntry(evictionStrategyType, key, value);
        kvMap.put(key, val);
        evictionStrategy.entryAdded(val);

    }

    @Override
    public void remove(K key) {
        evictionStrategy.entryRemoved(kvMap.get(key));
        kvMap.remove(key);
    }

    @Override
    public void evict() {
        CacheEntry<K, V> entry = evictionStrategy.evict();
        kvMap.remove(entry.getKey());
    }

    @Override
    public V get(K key) {
        if (!kvMap.containsKey(key)) {
            return null;
        }
        evictionStrategy.entryAccessed(kvMap.get(key));
        return kvMap.get(key).getValue();
    }

    @Override
    public boolean contains(K key) {
        return kvMap.containsKey(key);
    }

    @Override
    public int getSize() {
        return kvMap.size();
    }

    @Override
    public boolean isEmpty() {
        return kvMap.isEmpty();
    }
}

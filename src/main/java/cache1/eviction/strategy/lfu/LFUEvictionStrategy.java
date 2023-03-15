package cache1.eviction.strategy.lfu;

import cache1.eviction.strategy.CacheEntry;
import cache1.eviction.strategy.EvictionStrategy;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFUEvictionStrategy<K, V> implements EvictionStrategy<K, V> {

    int minFrequency;
    Map<Integer, LinkedHashSet<LFUCacheEntry<K, V>>> freqMap;

    public LFUEvictionStrategy() {
        freqMap = new HashMap<>();
    }

    @Override
    public CacheEntry<K, V> evict() {
        Set<LFUCacheEntry<K,V>> keys = freqMap.get(minFrequency);
        LFUCacheEntry<K, V> x = freqMap.get(minFrequency).iterator().next();
        freqMap.get(minFrequency).remove(freqMap.get(minFrequency).iterator().next());
        return x;
    }

    @Override
    public void entryAdded(CacheEntry<K, V> entry) {
        LFUCacheEntry<K, V> casted = (LFUCacheEntry<K, V>) entry;
        minFrequency = 1;
        freqMap.computeIfAbsent(minFrequency, x -> new LinkedHashSet<>()).add(casted);
    }

    @Override
    public void entryRemoved(CacheEntry<K, V> entry) {
        LFUCacheEntry<K, V> casted = (LFUCacheEntry<K, V>) entry;
        int freq = casted.getFrequency();
        Set<LFUCacheEntry<K,V>> keys = freqMap.get(freq);
        keys.remove(casted);
    }

    @Override
    public void entryAccessed(CacheEntry<K, V> entry) {
        LFUCacheEntry<K, V> casted = (LFUCacheEntry<K, V>) entry;
        int freq = casted.getFrequency();
        Set<LFUCacheEntry<K,V>> keys = freqMap.get(freq);
        keys.remove(casted);
        freqMap.computeIfAbsent(freq + 1, x -> new LinkedHashSet<>()).add(casted);
        if(keys.isEmpty()){
            minFrequency++;
        }
    }
}

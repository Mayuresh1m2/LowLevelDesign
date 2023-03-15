package cache1.eviction.strategy;

public interface EvictionStrategy<K, V> {
    CacheEntry<K, V> evict();

    void entryAccessed(CacheEntry<K, V> entry);

    void entryAdded(CacheEntry<K, V> entry);

    void entryRemoved(CacheEntry<K, V> entry);
}

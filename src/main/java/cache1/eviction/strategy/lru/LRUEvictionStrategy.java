package cache1.eviction.strategy.lru;

import cache1.eviction.strategy.CacheEntry;
import cache1.eviction.strategy.EvictionStrategy;

public class LRUEvictionStrategy<K, V> implements EvictionStrategy<K, V> {
    DLL<K, V> dll;

    public LRUEvictionStrategy() {
        dll = new DLL<>();
    }

    @Override
    public CacheEntry<K, V> evict() {
        return dll.getUpdatedTail();
    }

    @Override
    public void entryAccessed(CacheEntry<K, V> entry) {
        dll.moveToFront(entry);
    }

    @Override
    public void entryAdded(CacheEntry<K, V> entry) {
        dll.addAtHead(entry);
    }

    @Override
    public void entryRemoved(CacheEntry<K, V> entry) {
        dll.removeNode(entry);
    }
}

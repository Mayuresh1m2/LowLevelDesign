package cache.cache;

import cache.input.CacheElement;
import cache.queue.QueueNode;

import java.util.*;


public class LFUCache<K, V> implements Cache<K, V> {
    Map<K, QueueNode<CacheElement<K, V>>> map;
    Queue<QueueNode<CacheElement<K, V>>> queue;
    int maxSize;

    public LFUCache(int size) {
        maxSize = size;
        map = new HashMap<>(maxSize);
        queue = new PriorityQueue<>((o1, o2) -> (o1.getFrequency() == o2.getFrequency()) ?
                (Long.compare(o1.getTimestamp(),
                        o2.getTimestamp())) : Long.compare(o1.getFrequency(),
                o2.getFrequency()));
    }

    @Override
    public boolean put(K key, V value) {
        if (map.size() == maxSize) {
            evictCache();
        }
        QueueNode<CacheElement<K, V>> node = new QueueNode<>(new CacheElement<>(key, value));
        map.put(key, node);
        queue.add(node);
        return true;
    }

    private void evictCache() {
        QueueNode<CacheElement<K, V>> remove = queue.remove();
        map.remove(remove.getValue().getKey());
        System.out.println("Evicting" + remove.getValue());
    }

    @Override
    public Optional<V> get(K key) {
        QueueNode<CacheElement<K, V>> node = map.get(key);
        if (node != null) {
            queue.remove(node);
            node.incrementFrequency();
            queue.add(node);
            return Optional.of(node.getValue().getValue());
        }
        return Optional.empty();

    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map = new HashMap<>(maxSize);
        queue = new PriorityQueue<>((o1, o2) -> (o1.getFrequency() == o2.getFrequency()) ? (Long.compare(o2.getTimestamp(),
                o1.getTimestamp())) : Long.compare(o1.getFrequency(), o2.getFrequency()));
    }
}

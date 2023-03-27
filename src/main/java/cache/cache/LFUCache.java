package cache.cache;

import cache.input.CacheElement;
import cache.queue.QueueNode;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class LFUCache<K, V> implements Cache<K, V> {
    final Lock readLock;
    final Lock writeLock;
    Map<K, QueueNode<CacheElement<K, V>>> map;
    ReentrantReadWriteLock lock;
    Queue<QueueNode<CacheElement<K, V>>> queue;
    int maxSize;

    public LFUCache(int size) {
        maxSize = size;
        map = new HashMap<>(maxSize);
        queue = new PriorityQueue<>((o1, o2) -> (o1.getFrequency() == o2.getFrequency()) ?
                (Long.compare(o1.getTimestamp(),
                        o2.getTimestamp())) : Long.compare(o1.getFrequency(),
                o2.getFrequency()));
        lock = new ReentrantReadWriteLock(true);
        readLock = lock.readLock();
        writeLock = lock.writeLock();


    }

    @Override
    public boolean put(K key, V value) {
        try {
            writeLock.lock();
            if (map.size() == maxSize) {
                evictCache();
            }
            QueueNode<CacheElement<K, V>> node = new QueueNode<>(new CacheElement<>(key, value));
            map.put(key, node);
            queue.add(node);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    private void evictCache() {
        QueueNode<CacheElement<K, V>> remove = queue.remove();
        map.remove(remove.getValue().getKey());
        System.out.println("Evicting" + remove.getValue());
    }

    @Override
    public Optional<V> get(K key) {
        try {
            readLock.lock();
            QueueNode<CacheElement<K, V>> node = map.get(key);
            if (node != null) {
                queue.remove(node);
                node.incrementFrequency();
                queue.add(node);
                return Optional.of(node.getValue().getValue());
            }
            return Optional.empty();
        } finally {
            readLock.unlock();
        }
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
        try {
            writeLock.lock();

            map = new HashMap<>(maxSize);
            queue = new PriorityQueue<>((o1, o2) -> (o1.getFrequency() == o2.getFrequency()) ? (Long.compare(o2.getTimestamp(),
                    o1.getTimestamp())) : Long.compare(o1.getFrequency(), o2.getFrequency()));
        } finally {
            writeLock.unlock();
        }
    }
}

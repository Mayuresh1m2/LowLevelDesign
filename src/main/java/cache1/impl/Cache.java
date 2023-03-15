package cache1.impl;

import cache1.storage.Storage;

public class Cache<K, V> {
    private final Storage<K, V> storage;
    int capacity;

    public Cache(int capacity, Storage<K, V> storage) {
        this.storage = storage;
        this.capacity = capacity;

    }

    public void put(K key, V value) {
        if (storage.getSize() == capacity) {
            storage.evict();
        }
        storage.put(key, value);
    }

    public V get(K key) {
        return storage.get(key);
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    public int getSize() {
        return storage.getSize();
    }
}

package cache1.storage;

public interface Storage<K, V> {
    void put(K key, V value);

    void remove(K key);
    void evict();
    V get(K key);

    boolean contains(K key);

    int getSize();

    boolean isEmpty();
}

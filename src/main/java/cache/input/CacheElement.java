package cache.input;

import lombok.Getter;

@Getter
public class CacheElement<K, V> {
    private final K key;
    private final V value;

    public CacheElement(K key, V value) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String toString() {
        return "CacheElement{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
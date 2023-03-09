package cache.cache;

import cache.dll.DoublyLinkedList;
import cache.dll.LinkedListNode;
import cache.input.CacheElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private Map<K, LinkedListNode<CacheElement<K, V>>> linkedListNodeMap;
    private DoublyLinkedList<CacheElement<K, V>> doublyLinkedList;

    public LRUCache(int size) {
        this.maxSize = size;
        this.linkedListNodeMap = new HashMap<>(size);
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Override
    public boolean put(K key, V value) {
        if (linkedListNodeMap.containsKey(key)) {
            return false;
        }
        if (linkedListNodeMap.size() == maxSize) {
            evictCache();

        }
        LinkedListNode<CacheElement<K, V>> node =
                new LinkedListNode<>(new CacheElement<>(key, value));
        linkedListNodeMap.put(key, node);
        doublyLinkedList.addToHead(node);
        return true;
    }

    private void evictCache() {
        LinkedListNode<CacheElement<K, V>> node = doublyLinkedList.removeFromTail();
        linkedListNodeMap.remove(node.getValue().getKey());
        System.out.println("Evicted " + node.getValue());

    }

    @Override
    public Optional<V> get(K key) {
        LinkedListNode<CacheElement<K, V>> node = linkedListNodeMap.get(key);
        if(node == null)return Optional.empty();
        doublyLinkedList.moveToHead(node);
        return Optional.of(node.getValue().getValue()) ;
    }

    @Override
    public int size() {
        return linkedListNodeMap.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedListNodeMap.isEmpty();
    }

    @Override
    public void clear() {
        linkedListNodeMap = new HashMap<>();
        doublyLinkedList = new DoublyLinkedList<>();
    }
}

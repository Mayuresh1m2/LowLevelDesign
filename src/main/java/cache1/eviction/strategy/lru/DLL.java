package cache1.eviction.strategy.lru;

import cache1.eviction.strategy.CacheEntry;

public class DLL<K, V> {
    LRUCacheEntry<K, V> dummyHead, dummyTail;

    public DLL() {
        dummyHead = new LRUCacheEntry<>(null, null);
        dummyTail = new LRUCacheEntry<>(null, null);
        dummyHead.setPrev(dummyTail);
        dummyTail.setNext(dummyHead);
    }

    public void addAtHead(CacheEntry<K, V> entry) {
        LRUCacheEntry<K, V> oldHeadPrev = dummyHead.getPrev();
        LRUCacheEntry<K, V> casted = ((LRUCacheEntry<K, V>) (entry));
        dummyHead.setPrev(casted);
        casted.setNext(dummyHead);
        casted.setPrev(oldHeadPrev);
        oldHeadPrev.setNext(casted);
    }

    public CacheEntry<K, V> removeNode(CacheEntry<K, V> entry) {
        LRUCacheEntry<K, V> node = ((LRUCacheEntry<K, V>) (entry));
        LRUCacheEntry<K, V> prev = node.getPrev();
        LRUCacheEntry<K, V> next = node.getNext();
        prev.setNext(node.getNext());
        next.setPrev(node.getPrev());
        node.setNext(null);
        node.setPrev(null);
        return node;

    }

    public void moveToFront(CacheEntry<K, V> node) {
        CacheEntry<K, V> toRemove = removeNode(node);
        addAtHead(toRemove);
    }

    public CacheEntry<K, V> getUpdatedTail() {
        LRUCacheEntry<K, V> oldTail = dummyTail.getNext();
        dummyTail.setNext(oldTail.getNext());
        oldTail.getNext().setPrev(dummyTail);
        oldTail.setNext(null);
        oldTail.setPrev(null);
        return oldTail;
    }
}

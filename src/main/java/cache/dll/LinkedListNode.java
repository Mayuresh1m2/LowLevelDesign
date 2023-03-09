package cache.dll;

import lombok.Getter;

public class LinkedListNode<V> {
    @Getter
    V value;
    LinkedListNode<V> prev, next;


    public LinkedListNode(V value) {
        this.value = value;
    }

    LinkedListNode<V> addNext(LinkedListNode<V> node) {
        this.next = node;
        return node;
    }

    LinkedListNode<V> addPrev(LinkedListNode<V> node) {
        this.prev = node;
        return node;
    }
}

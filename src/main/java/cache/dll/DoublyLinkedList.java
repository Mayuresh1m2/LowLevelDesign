package cache.dll;

public class DoublyLinkedList<T> {
    LinkedListNode<T> head, tail;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public void addToHead(LinkedListNode<T> t) {
        if (head == null) {
            head = tail = t;
            return;
        }
        head = head.addNext(t);
    }

    public void addToTail(LinkedListNode<T> t) {
        if (head == null) {
            head = tail = t;
            return;
        }
        tail = tail.addPrev(t);
    }

    /**
     * Assumption is that the node is present in the list
     *
     * @param t node to be moved to the head
     */
    public void moveToHead(LinkedListNode<T> t) {
        LinkedListNode<T> prev = t.prev;
        LinkedListNode<T> next = t.next;
        if (t.next == null) {
            //Already at head
            return;
        }
        if (prev == null) {
            tail = next;
            next.prev = null;
        }
        t.prev = null;
        t.next = null;
        addToHead(t);

    }

    public LinkedListNode<T> removeFromTail() {
        LinkedListNode<T> oldTail = tail;
        oldTail.next = null;
        tail = tail.prev;
        if (tail == null) {
            head = tail = null;
        } else {
            tail.prev = null;
        }

        return oldTail;

    }
}

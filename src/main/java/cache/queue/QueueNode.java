package cache.queue;

import lombok.Getter;

public class QueueNode<V> {
    @Getter
    V value;
    @Getter
    long frequency;
    @Getter
    long timestamp;

    public QueueNode(V value) {
        this.value = value;
        frequency = 0;
        timestamp = System.currentTimeMillis();
    }

    public void incrementFrequency() {
        timestamp = System.currentTimeMillis();
        frequency++;
    }
}

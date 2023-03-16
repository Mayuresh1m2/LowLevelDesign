package distributedqueue.consumer;

public class ConsumerRecord<K, V> {
    String topic;
    int partition;
    int offset;
    long timestamp;
    K key;
    V value;

}

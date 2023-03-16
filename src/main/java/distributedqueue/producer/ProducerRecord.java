package distributedqueue.producer;

import distributedqueue.topic.Topic;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class ProducerRecord<K, V> {
    @NonNull
    Topic topic;
    int partition;
    long timestamp;
    @NonNull
    K key;
    @NonNull
    V value;
}

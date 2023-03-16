package distributedqueue.producer;

public interface Producer<K, V> {
    void sendMessageToTopic(ProducerRecord<K, V> producerRecord);
}

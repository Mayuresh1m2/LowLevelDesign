package distributedqueue;

import distributedqueue.consumer.Consumer;
import distributedqueue.exception.TopicExistsException;
import distributedqueue.impl.InMemoryDistributedQueue;
import distributedqueue.producer.InMemoryDistributedQueueProducer;
import distributedqueue.topic.Topic;

public class Application {
    public static void main(String[] args) throws TopicExistsException {
        DistributedQueue distributedQueue = new InMemoryDistributedQueue();
        InMemoryDistributedQueueProducer inMemoryDistributedQueueProducer1 = new InMemoryDistributedQueueProducer("prod-1", distributedQueue);
        InMemoryDistributedQueueProducer inMemoryDistributedQueueProducer2 = new InMemoryDistributedQueueProducer("prod-2", distributedQueue);
        Topic topic1 =
                Topic.builder().id("1").name("Topic-1").createdTime(System.currentTimeMillis()).build();
        inMemoryDistributedQueueProducer1.addTopic(topic1);
        distributedQueue.addTopic(topic1);
        Topic topic2 =
                Topic.builder().id("2").name("Topic-2").createdTime(System.currentTimeMillis()).build();
        inMemoryDistributedQueueProducer2.addTopic(topic2);
        distributedQueue.addTopic(topic2);
        Consumer consumer1 = new Consumer("Cons-1", distributedQueue);
        consumer1.addTopicToPoll(topic1);
        Consumer consumer2 = new Consumer("Cons-2", distributedQueue);
        consumer2.addTopicToPoll(topic2);
        inMemoryDistributedQueueProducer1.startSending();
        inMemoryDistributedQueueProducer2.startSending();


    }
}

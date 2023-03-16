package distributedqueue;

import distributedqueue.exception.TopicDoesNotExistException;
import distributedqueue.exception.TopicExistsException;
import distributedqueue.message.Message;
import distributedqueue.topic.Topic;

public interface DistributedQueue {
    void addTopic(Topic topic) throws TopicExistsException;

    void removeTopic(Topic topic) throws TopicDoesNotExistException;

    void sendMessageToTopic(Message message, Topic topics);

    String pollTopic(Topic topic);
}

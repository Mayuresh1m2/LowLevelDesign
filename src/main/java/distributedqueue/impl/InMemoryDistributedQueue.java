package distributedqueue.impl;

import distributedqueue.DistributedQueue;
import distributedqueue.exception.TopicDoesNotExistException;
import distributedqueue.exception.TopicExistsException;
import distributedqueue.message.InMemoryMessageHandler;
import distributedqueue.message.Message;
import distributedqueue.message.MessageHandler;
import distributedqueue.topic.Topic;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDistributedQueue implements DistributedQueue {
    Map<Topic, MessageHandler> topicMessageHandlerMap;

    public InMemoryDistributedQueue() {
        this.topicMessageHandlerMap = new HashMap<>();
    }

    @Override
    public void addTopic(Topic topic) throws TopicExistsException {
        if (topicMessageHandlerMap.containsKey(topic)) {
            throw new TopicExistsException(String.format(
                    "Topic to be created [ %s ], exists already", topic));
        }
        topicMessageHandlerMap.put(topic, new InMemoryMessageHandler(0));
    }

    @Override
    public void removeTopic(Topic topic) throws TopicDoesNotExistException {
        if (!topicMessageHandlerMap.containsKey(topic)) {
            throw new TopicDoesNotExistException(String.format("Topic to be removed [ %s ]" +
                    "does not exist", topic));
        }
        topicMessageHandlerMap.remove(topic);

    }

    @Override
    public void sendMessageToTopic(Message message, Topic topic) {
        topicMessageHandlerMap.get(topic).sendMessage(message);
    }


    @Override
    public String pollTopic(Topic topic) {
        return topicMessageHandlerMap.get(topic).receiveMessage();
    }
}

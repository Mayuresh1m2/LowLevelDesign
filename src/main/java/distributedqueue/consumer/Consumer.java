package distributedqueue.consumer;

import distributedqueue.DistributedQueue;
import distributedqueue.topic.Topic;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Runnable {
    String id;
    DistributedQueue distributedQueue;
    ExecutorService executorService;
    private List<Topic> topics;

    public Consumer(String id, DistributedQueue distributedQueue) {
        this.id = id;
        this.distributedQueue = distributedQueue;
        topics = new LinkedList<>();
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);
    }

    public void addTopicToPoll(Topic topic) {
        topics.add(topic);
    }

    public void poll() {
        for (Topic topic : topics) {
            System.out.println(this + " " + distributedQueue.pollTopic(topic));
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            poll();
        }

    }

    public void stopConsumer() {
        executorService.shutdown();
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id='" + id + '\'' +
                '}' + ' ';
    }
}

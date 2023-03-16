package distributedqueue.producer;

import distributedqueue.DistributedQueue;
import distributedqueue.message.Message;
import distributedqueue.topic.Topic;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDistributedQueueProducer implements Runnable,Producer<String,String> {
    List<Topic> topics;
    String id;
    DistributedQueue distributedQueue;
    ExecutorService executorService;
    private AtomicInteger i;


    public InMemoryDistributedQueueProducer(String id, DistributedQueue distributedQueue) {
        this.topics = new LinkedList<>();
        this.id = id;
        this.distributedQueue = distributedQueue;
        executorService = Executors.newSingleThreadExecutor();
        i = new AtomicInteger(0);
    }

    void sendMessage(Message message, Topic topic) {
        distributedQueue.sendMessageToTopic(message, topic);
    }

    public void addTopic(Topic topic) {
        topics.add(topic);

    }

   public void startSending(){
        executorService.execute(this);
   }
    @Override
    public void run() {
        try {
            while (true) {

                Message message =
                        Message.builder().messageId(this + "-ID-" + i).message(this +
                                " " + i).messageTime

                                (System.currentTimeMillis()).build();

                for (Topic topic : topics) {
                    sendMessage(message, topic);
                    System.out.println( this  + " sending " + message);
                }
                i.incrementAndGet();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "InMemoryDistributedQueueProducer" +
                "id=" + id ;
    }

    @Override
    public void sendMessageToTopic(ProducerRecord<String, String> producerRecord) {

    }
}

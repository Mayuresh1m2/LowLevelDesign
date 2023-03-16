package distributedqueue.message;

public interface MessageHandler {
    void sendMessage(Message message);

    String receiveMessage();
}

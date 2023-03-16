package distributedqueue.message;

import java.util.LinkedList;
import java.util.List;

public class InMemoryMessageHandler implements MessageHandler {
    List<Message> messages;
    int offset;

    public InMemoryMessageHandler(int offset) {
        this.offset = offset;
        messages = new LinkedList<>();
    }

    @Override
    public void sendMessage(Message message) {
        messages.add(message);
    }

    @Override
    public String receiveMessage() {
        if(messages.size()>offset)
        return messages.get(offset++).toString();
        return "";
    }
}

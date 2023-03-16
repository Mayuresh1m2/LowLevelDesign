package distributedqueue.message;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class Message {
    @NonNull
    String messageId;
    String message;

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", messageTime=" + messageTime +
                '}';
    }

    long messageTime;


}

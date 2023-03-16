package distributedqueue.exception;

public class TopicExistsException extends Throwable {
    public TopicExistsException(String message) {
        System.out.println(message);
    }
}

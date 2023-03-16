package distributedqueue.exception;

public class TopicDoesNotExistException extends Throwable {
    public TopicDoesNotExistException(String message) {
        System.out.println(message);
    }
}

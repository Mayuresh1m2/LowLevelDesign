package distributedqueue.topic;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class Topic {

    @NonNull
    String id;
    @NonNull
    String name;
    int partitionId;
    @NonNull
    long createdTime;

    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}

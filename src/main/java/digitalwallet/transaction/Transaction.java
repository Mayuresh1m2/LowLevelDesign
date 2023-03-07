package digitalwallet.transaction;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Transaction {
    @Getter
    String fromId;
    @Getter
    String toId;
    long amountTransferred;
    long time;
    Status status;
    Type type;

    @Override
    public String toString() {
        return "Transaction{" +
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", amountTransferred=" + amountTransferred +
                ", time=" + time +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

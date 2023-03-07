package digitalwallet.wallet;

import digitalwallet.exception.InsufficientFundException;
import lombok.Builder;
import lombok.Getter;

@Builder
public class Wallet {
    @Getter
    String walletId;
    String userName;
    @Getter
    long remainingAmount;
    long createdDate;


    public void addMoney(long amount) {
        remainingAmount += amount;
    }

    public void removeMoney(long amount) throws InsufficientFundException {
        if (remainingAmount - amount < 0) {
            throw new InsufficientFundException("Insufficient funds - please add funds.");
        }
        remainingAmount -= amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId='" + walletId + '\'' +
                ", userName='" + userName + '\'' +
                ", remainingAmount=" + remainingAmount +
                ", createdDate=" + createdDate +
                '}';
    }
}

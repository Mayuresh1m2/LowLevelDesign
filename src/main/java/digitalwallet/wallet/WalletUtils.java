package digitalwallet.wallet;

import java.util.UUID;

public final class WalletUtils {
    public static String generateWalletId() {
        return UUID.randomUUID().toString();
    }
}

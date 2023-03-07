package digitalwallet.offers;

import digitalwallet.wallet.Wallet;

public final class Offers {

    public static boolean ifWalletsHaveSameAmount(Wallet w1, Wallet w2) {
        return w1.getRemainingAmount() == w2.getRemainingAmount();
    }
}

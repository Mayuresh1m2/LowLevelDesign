package digitalwallet.exception;

public class WalletAlreadyCreatedException extends Throwable {
    public WalletAlreadyCreatedException(String message) {
        System.out.println(message);
    }
}

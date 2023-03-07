package digitalwallet.dao;


import digitalwallet.exception.WalletAlreadyCreatedException;
import digitalwallet.transaction.Status;
import digitalwallet.wallet.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletDao {
    void createWallet(Wallet wallet) throws WalletAlreadyCreatedException;

    Status addMoneyToWallet(String walletId, long money);

    Status transferToWallet(String from, String to, long money);

    void printStatement(String userId);

    List<Wallet> getAllWallets();

    Optional<Wallet> getWallet(String userId);

}

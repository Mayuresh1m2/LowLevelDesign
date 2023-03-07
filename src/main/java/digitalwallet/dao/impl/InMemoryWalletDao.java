package digitalwallet.dao.impl;

import digitalwallet.dao.WalletDao;
import digitalwallet.exception.InsufficientFundException;
import digitalwallet.exception.WalletAlreadyCreatedException;
import digitalwallet.transaction.Status;
import digitalwallet.wallet.Wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryWalletDao implements WalletDao {
    Map<String, Wallet> wallets;

    public InMemoryWalletDao() {
        wallets = new HashMap<>();
    }

    @Override
    public void createWallet(Wallet wallet) throws WalletAlreadyCreatedException {
        if (wallets.containsKey(wallet.getWalletId())) {
            throw new WalletAlreadyCreatedException("Wallet already exists");
        }
        wallets.put(wallet.getWalletId(), wallet);
    }

    @Override
    public Status addMoneyToWallet(String walletId, long money) {
        wallets.get(walletId).addMoney(money);
        return Status.SUCCESS;
    }

    @Override
    public Status transferToWallet(String from, String to, long money) {
        try {
            wallets.get(from).removeMoney(money);
            wallets.get(to).addMoney(money);
        } catch (InsufficientFundException e) {
            return Status.FAILURE;
        }

        return Status.SUCCESS;
    }

    @Override
    public void printStatement(String userId) {
        if (wallets.containsKey(userId)) {
            System.out.println(wallets.get(userId));
            return;
        }
        System.out.println("User with ID " + userId + " does not holds a wallet");

    }

    @Override
    public List<Wallet> getAllWallets() {
        return wallets.values().stream().toList();
    }

    @Override
    public Optional<Wallet> getWallet(String userId) {
        return Optional.ofNullable(wallets.get(userId));
    }

}

package digitalwallet;

import digitalwallet.dao.TransactionDao;
import digitalwallet.dao.WalletDao;
import digitalwallet.exception.WalletAlreadyCreatedException;
import digitalwallet.offers.Offers;
import digitalwallet.transaction.Status;
import digitalwallet.transaction.Type;
import digitalwallet.wallet.Wallet;
import digitalwallet.wallet.WalletUtils;
import lombok.NonNull;

public class WalletManager {

    private final TransactionDao transactionDao;
    private final WalletDao walletDao;

    public WalletManager(@NonNull final TransactionDao transactionDao, @NonNull final WalletDao walletDao) {
        this.transactionDao = transactionDao;
        this.walletDao = walletDao;
    }

    // createWallet
    public String createWallet(String userName, long initialMoney) throws WalletAlreadyCreatedException {
        Wallet wallet = Wallet.builder()
                .walletId(WalletUtils.generateWalletId())
                .userName(userName)
                .createdDate(System.currentTimeMillis())
                .remainingAmount(0)
                .build();
        walletDao.createWallet(wallet);
        addMoneyToWallet(wallet.getWalletId(), initialMoney);
        return wallet.getWalletId();

    }

    public void addMoneyToWallet(String walletId, long amount) {
        Status status = walletDao.addMoneyToWallet(walletId, amount);
        transactionDao.addTransaction(amount, walletId, walletId, System.currentTimeMillis(), status, Type.TOP_UP);
    }
    // transferAmount

    public void transferAmount(String from, String to, long amount) {
        long time = System.currentTimeMillis();
        Status status = walletDao.transferToWallet(from, to, amount);
        transactionDao.addTransaction(amount, from, to, time, status, Type.TRANSFER);

    }

    // accountStatement
    public void printAccountStatement(String userId) {
        walletDao.printStatement(userId);
        transactionDao.printStatement(userId);
    }
    // overview

    public void overview() {
        for (Wallet wallet : walletDao.getAllWallets()) {
            System.out.println(wallet);
        }
    }
}

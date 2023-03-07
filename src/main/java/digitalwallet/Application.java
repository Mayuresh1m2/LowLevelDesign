package digitalwallet;

import digitalwallet.dao.impl.InMemoryTransactionDao;
import digitalwallet.dao.impl.InMemoryWalletDao;
import digitalwallet.exception.WalletAlreadyCreatedException;

public class Application {

    public static void main(String[] args) throws WalletAlreadyCreatedException {
        WalletManager walletManager = new WalletManager(new InMemoryTransactionDao(), new InMemoryWalletDao());
        String user1WalletId = walletManager.createWallet("User1", 1000L);
        String user2WalletId = walletManager.createWallet("User2", 5000L);
        System.out.println("----------------------------Overview-----------------------------------");
        walletManager.overview();
        System.out.println(String.format("----------------------------Statement %s--------------------------------------", user1WalletId));
        walletManager.printAccountStatement(user1WalletId);
        System.out.println(String.format("----------------------------Statement %s--------------------------------------", user2WalletId));
        walletManager.printAccountStatement(user2WalletId);
        System.out.println("---------------------------------Transfer 400-------------------------------");
        walletManager.transferAmount(user1WalletId, user2WalletId, 400L);
        walletManager.printAccountStatement(user1WalletId);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("----------------------------Overview-----------------------------------");
        walletManager.overview();

    }
}

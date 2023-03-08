[Q link](https://techwithkp.com/digital-wallet-design-machine-coding-round-solution/)
Mandatory Requirements

    You are supposed to create a digital wallet system that allows people to transfer amounts between their wallets.
    The wallet uses its own currency known as FkRupee(F₹).
    The account balance cannot drop below F₹ 0.00.
    The smallest amount that can be transferred between wallets is 0.0001.
    The user should be presented with options for each action. And the options are as follows:
        Create Wallet – This option should create a wallet for the user.
        Transfer Amount – This option should enable the transfer of funds from one account to the other.
        Account Statement – This option should display the account statement for the specified user.
        Overview – This option should display all the account numbers currently in the system. Additionally, it should show the current balances for these accounts.
        Exit – The system should exit.

Optional Requirements

These are the requirements that are not mandatory, but good to have. Let’s go through the optional requirements.

    Offer 1 – When the amount is transferred from user A to user B, F₹ 10 must be added to both the sender and receiver wallets if their balance is the same.
    Offer 2 – Whenever Offer 2 is selected, the top 3 customers with the highest number of transactions will get F₹ 10, F₹ 5, and F₹ 2 as rewards. If there is a tie between customers, i.e., if customers have the same number of transactions, then the customer having higher account balance should be given preference. If there is still a tie, i.e., the customers have the same balance, then the customer whose account was created first should be given preference.
    Add one more option called FixedDeposit <fd_amount>. And whenever the option is selected, an amount equal to <fd_amount> is parked for. If for the next 5 transactions, the account balance for that account remains above <fd_amount>, the user gets F₹ 10 as interest in their account. And if the account balance goes below <fd_amount>, then the FD should be dissolved and the user would need to give the FixedDeposit command again to start a new FD.
    Consider another added bonus, display the <fd_amount> and remaining transactions in the Overview and Statement command also.

Questions
1. Can there be multiple fixed deposits
2. Can a user exists without a wallet ?
3. Should transaction statement reflect addition of money in case of offer 1/2?
4. Are there any conditions in which wallet should not be created ?
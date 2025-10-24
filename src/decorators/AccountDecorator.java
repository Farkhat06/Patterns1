package decorators;

import accounts.Account;

public abstract class AccountDecorator implements Account {
    protected Account account;

    public AccountDecorator(Account account) {
        this.account = account;
    }

    public String getAccountNumber() {
        return account.getAccountNumber();
    }

    public double getBalance() {
        return account.getBalance();
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }
}

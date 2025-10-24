package decorators;

import accounts.Account;

public abstract class AccountDecorator implements Account {
    protected Account account;

    public AccountDecorator(Account account) {
        this.account = account;
    }

    @Override
    public String getAccountNumber() {
        return account.getAccountNumber();
    }

    @Override
    public double getBalance() {
        return account.getBalance();
    }

    @Override
    public void deposit(double amount) {
        account.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    @Override
    public String getAccountType() {
        return account.getAccountType();
    }
}

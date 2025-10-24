package facade;

import java.util.ArrayList;
import java.util.List;
import accounts.Account;
import decorators.InsuranceDecorator;
import decorators.TaxOptimizerDecorator;
import decorators.RewardPointsDecorator;
import accounts.SavingsAccount;
import accounts.InvestmentAccount;

public class BankingFacade {
    private List<Account> accounts;

    public BankingFacade() {
        this.accounts = new ArrayList<>();
    }

    public Account openSavings(double balance) {
        Account account = new InsuranceDecorator(
            new RewardPointsDecorator(new SavingsAccount(balance), 1.0), 
            1000.0
        );
        accounts.add(account);
        System.out.println("Savings account opened");
        return account;
    }

    public Account openInvestment(double balance) {
        Account account = new InsuranceDecorator(
            new TaxOptimizerDecorator(new InvestmentAccount(balance), 0.1),
            2000.0
        );
        accounts.add(account);
        System.out.println("Investment account opened");
        return account;
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
    }

    public void showAccounts() {
        System.out.println("Accounts:");
        for (Account account : accounts) {
            System.out.println(account.getAccountNumber() + ": $" + account.getBalance());
        }
    }
}

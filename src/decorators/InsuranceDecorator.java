package decorators;

import accounts.Account;

public class InsuranceDecorator extends AccountDecorator {
    private double coverage;

    public InsuranceDecorator(Account account, double coverage) {
        super(account);
        this.coverage = coverage;
    }

    public void deposit(double amount) {
        account.deposit(amount);
        System.out.println("Insurance: $" + coverage);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
        if (account.getBalance() < 100) {
            System.out.println("Low balance warning");
        }
    }
}

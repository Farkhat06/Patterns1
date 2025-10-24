package decorators;

import accounts.Account;

public class TaxOptimizerDecorator extends AccountDecorator {
    private double rate;

    public TaxOptimizerDecorator(Account account, double rate) {
        super(account);
        this.rate = rate;
    }

    public void deposit(double amount) {
        account.deposit(amount);
        double saved = amount * rate;
        System.out.println("Tax saved: $" + saved);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
        System.out.println("Tax optimized");
    }
}

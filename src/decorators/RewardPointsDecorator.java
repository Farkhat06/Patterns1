package decorators;

import accounts.Account;

public class RewardPointsDecorator extends AccountDecorator {
    private int points;

    public RewardPointsDecorator(Account account, double rate) {
        super(account);
        this.points = 0;
    }

    public void deposit(double amount) {
        account.deposit(amount);
        points += (int) amount;
        System.out.println("Points earned: " + points);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
        if (points > 100) {
            System.out.println("Can use points for fees");
        }
    }
}

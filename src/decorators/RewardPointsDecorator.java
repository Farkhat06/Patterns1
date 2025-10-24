package decorators;

import accounts.Account;

public class RewardPointsDecorator extends AccountDecorator {
    private int rewardPoints;
    private double pointsRate;

    public RewardPointsDecorator(Account account, double pointsRate) {
        super(account);
        this.pointsRate = pointsRate;
        this.rewardPoints = 0;
    }

    @Override
    public void deposit(double amount) {
        account.deposit(amount);
        int earnedPoints = (int) (amount * pointsRate);
        rewardPoints += earnedPoints;
        System.out.println("Earned " + earnedPoints + " reward points! Total points: " + rewardPoints);
    }

    @Override
    public void withdraw(double amount) {
        account.withdraw(amount);
        if (rewardPoints >= 1000 && amount <= 100) {
            System.out.println("💡 You can use " + (rewardPoints / 10) + " points to reduce withdrawal fees!");
        }
    }

    @Override
    public String getDescription() {
        return account.getDescription() + " + Reward Points System (" + pointsRate + " points per $)";
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void redeemPoints(int points) {
        if (points <= rewardPoints) {
            rewardPoints -= points;
            double cashValue = points / 100.0;
            account.deposit(cashValue);
            System.out.println("Redeemed " + points + " points for $" + cashValue + ". Remaining points: " + rewardPoints);
        } else {
            System.out.println("Insufficient points for redemption. Available: " + rewardPoints);
        }
    }
}

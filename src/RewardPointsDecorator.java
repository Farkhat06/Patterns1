public class RewardPointsDecorator extends AccountDecorator {
    public RewardPointsDecorator(Account account) {
        super(account);
    }

    public void addRewardPoints() {
        System.out.println("Reward points system added to the account.");
    }
}

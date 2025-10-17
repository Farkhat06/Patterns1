public class BankingFacade {
    public Account openAccountWithBenefits() {
        Account account = new SavingsAccount();
        account = new InsuranceDecorator(account);
        account = new RewardPointsDecorator(account);
        System.out.println("Savings account created with Insurance and Reward Points.");
        return account;
    }

    public Account investWithSafetyMode() {
        Account account = new InvestmentAccount();
        account = new TaxOptimizerDecorator(account);
        account = new InsuranceDecorator(account);
        System.out.println("Investment account created with Tax Optimization and Insurance.");
        return account;
    }

    public void closeAccount(Account account) {
        System.out.println("Account closed with balance: " + account.getBalance());
    }
}

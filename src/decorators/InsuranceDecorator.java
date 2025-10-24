package decorators;

import accounts.Account;

public class InsuranceDecorator extends AccountDecorator {
    private double insuranceCoverage;
    private double insurancePremium;

    public InsuranceDecorator(Account account, double coverage) {
        super(account);
        this.insuranceCoverage = coverage;
        this.insurancePremium = coverage * 0.01;
    }

    @Override
    public void deposit(double amount) {
        account.deposit(amount);
        System.out.println("Insurance coverage: $" + insuranceCoverage + " (Premium: $" + insurancePremium + "/month)");
    }

    @Override
    public void withdraw(double amount) {
        account.withdraw(amount);
        if (account.getBalance() < insuranceCoverage * 0.1) {
            System.out.println("⚠️  Warning: Balance below 10% of insurance coverage. Consider maintaining higher balance.");
        }
    }

    @Override
    public String getDescription() {
        return account.getDescription() + " + Insurance Coverage ($" + insuranceCoverage + ")";
    }

    public double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public double getInsurancePremium() {
        return insurancePremium;
    }
}

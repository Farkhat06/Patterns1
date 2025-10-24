package decorators;

import accounts.Account;

public class TaxOptimizerDecorator extends AccountDecorator {
    private double taxSavings;
    private double taxOptimizationRate;

    public TaxOptimizerDecorator(Account account, double optimizationRate) {
        super(account);
        this.taxOptimizationRate = optimizationRate;
        this.taxSavings = 0;
    }

    @Override
    public void deposit(double amount) {
        account.deposit(amount);
        double taxBenefit = amount * taxOptimizationRate;
        taxSavings += taxBenefit;
        System.out.println("Tax optimization applied: $" + taxBenefit + " saved. Total tax savings: $" + taxSavings);
    }

    @Override
    public void withdraw(double amount) {
        account.withdraw(amount);
        System.out.println("Tax-optimized withdrawal from " + account.getAccountType());
    }

    @Override
    public String getDescription() {
        return account.getDescription() + " + Tax Optimization (" + (taxOptimizationRate * 100) + "% savings)";
    }

    public double getTaxSavings() {
        return taxSavings;
    }

    public double getTaxOptimizationRate() {
        return taxOptimizationRate;
    }
}

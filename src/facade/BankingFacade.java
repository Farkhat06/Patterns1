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
    private int customerId;

    public BankingFacade(int customerId) {
        this.customerId = customerId;
        this.accounts = new ArrayList<>();
    }

    public Account openAccountWithBenefits(double initialBalance, double insuranceCoverage, double pointsRate) {
        System.out.println("\n🏦 Opening Premium Savings Account for Customer " + customerId);
        
        Account baseAccount = new SavingsAccount(initialBalance);
        Account decoratedAccount = new InsuranceDecorator(
            new RewardPointsDecorator(baseAccount, pointsRate), 
            insuranceCoverage
        );
        
        accounts.add(decoratedAccount);
        System.out.println("✅ Account created: " + decoratedAccount.getDescription());
        return decoratedAccount;
    }

    public Account investWithSafetyMode(double initialInvestment, double taxOptimizationRate, double insuranceCoverage) {
        System.out.println("\n📈 Opening Safe Investment Account for Customer " + customerId);
        
        Account baseAccount = new InvestmentAccount(initialInvestment);
        Account decoratedAccount = new InsuranceDecorator(
            new TaxOptimizerDecorator(baseAccount, taxOptimizationRate),
            insuranceCoverage
        );
        
        accounts.add(decoratedAccount);
        System.out.println("✅ Investment account created: " + decoratedAccount.getDescription());
        return decoratedAccount;
    }

    public void deposit(Account account, double amount) {
        System.out.println("\n💰 Processing deposit...");
        account.deposit(amount);
    }

    public void withdraw(Account account, double amount) {
        System.out.println("\n💸 Processing withdrawal...");
        account.withdraw(amount);
    }

    public void closeAccount(Account account) {
        System.out.println("\n🔒 Closing account " + account.getAccountNumber());
        
        if (account instanceof InsuranceDecorator) {
            InsuranceDecorator insurance = (InsuranceDecorator) account;
            System.out.println("📋 Insurance coverage terminated. Final coverage: $" + insurance.getInsuranceCoverage());
        }
        
        if (account instanceof TaxOptimizerDecorator) {
            TaxOptimizerDecorator taxOpt = (TaxOptimizerDecorator) account;
            System.out.println("📊 Final tax savings: $" + taxOpt.getTaxSavings());
        }
        
        if (account instanceof RewardPointsDecorator) {
            RewardPointsDecorator rewards = (RewardPointsDecorator) account;
            System.out.println("🎁 Final reward points: " + rewards.getRewardPoints());
            if (rewards.getRewardPoints() > 0) {
                System.out.println("💡 Redeeming remaining points...");
                rewards.redeemPoints(rewards.getRewardPoints());
            }
        }
        
        accounts.remove(account);
        System.out.println("✅ Account " + account.getAccountNumber() + " closed successfully");
    }

    public void displayAccountSummary() {
        System.out.println("\n📊 Account Summary for Customer " + customerId);
        System.out.println("=" .repeat(50));
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        for (Account account : accounts) {
            System.out.println("Account: " + account.getAccountNumber());
            System.out.println("Type: " + account.getAccountType());
            System.out.println("Balance: $" + account.getBalance());
            System.out.println("Features: " + account.getDescription());
            System.out.println("-".repeat(30));
        }
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }
}

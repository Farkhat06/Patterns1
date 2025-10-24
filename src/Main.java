import accounts.Account;
import accounts.SavingsAccount;
import decorators.InsuranceDecorator;
import decorators.TaxOptimizerDecorator;
import decorators.RewardPointsDecorator;
import facade.BankingFacade;
import accounting.Transaction;
import accounting.Ledger;
import currency.CurrencyConverter;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏦 Digital Banking & Investment System Demo");
        System.out.println("=" .repeat(60));
        
        BankingFacade bankingFacade = new BankingFacade(12345);
        
        System.out.println("\n📋 DEMO 1: Premium Savings Account");
        Account premiumSavings = bankingFacade.openAccountWithBenefits(
            5000.0,
            10000.0,
            2.0
        );
        
        bankingFacade.deposit(premiumSavings, 1000.0);
        bankingFacade.withdraw(premiumSavings, 500.0);
        
        System.out.println("\n📋 DEMO 2: Safe Investment Account");
        Account safeInvestment = bankingFacade.investWithSafetyMode(
            10000.0,
            0.15,
            20000.0
        );
        
        bankingFacade.deposit(safeInvestment, 2000.0);
        bankingFacade.withdraw(safeInvestment, 1000.0);
        
        System.out.println("\n📋 DEMO 3: Custom Account with All Features");
        Account baseAccount = new SavingsAccount(3000.0);
        Account customAccount = new RewardPointsDecorator(
            new TaxOptimizerDecorator(
                new InsuranceDecorator(baseAccount, 15000.0),
                0.10
            ),
            1.5
        );
        
        System.out.println("Custom account created: " + customAccount.getDescription());
        customAccount.deposit(2000.0);
        customAccount.withdraw(500.0);
        
        bankingFacade.displayAccountSummary();
        
        System.out.println("\n📋 DEMO 4: Account Closure");
        bankingFacade.closeAccount(premiumSavings);
        
        System.out.println("\n📊 Final Account Summary");
        bankingFacade.displayAccountSummary();
        
        System.out.println("\n📋 DEMO 5: Accounting & Transaction System");
        System.out.println("Creating transactions and ledger...");
        
        Ledger bankLedger = new Ledger();
        
        Transaction deposit1 = new Transaction("SAV1001", Transaction.TransactionType.DEPOSIT, 1000.0, "Initial deposit");
        Transaction deposit2 = new Transaction("SAV1001", Transaction.TransactionType.DEPOSIT, 500.0, "Salary deposit");
        Transaction withdrawal1 = new Transaction("SAV1001", Transaction.TransactionType.WITHDRAWAL, 200.0, "ATM withdrawal");
        Transaction fee1 = new Transaction("SAV1001", Transaction.TransactionType.FEE, 5.0, "Monthly maintenance fee");
        
        deposit1.complete();
        deposit2.complete();
        withdrawal1.complete();
        fee1.complete();
        
        bankLedger.addTransaction(deposit1);
        bankLedger.addTransaction(deposit2);
        bankLedger.addTransaction(withdrawal1);
        bankLedger.addTransaction(fee1);
        
        bankLedger.displayAccountStatement("SAV1001");
        bankLedger.displayLedgerSummary();
        
        System.out.println("\n📋 DEMO 6: Currency Exchange System");
        System.out.println("Demonstrating currency conversion...");
        
        CurrencyConverter converter = new CurrencyConverter();
        
        converter.displayExchangeRates("USD");
        
        double usdAmount = 1000.0;
        double eurAmount = converter.convert(usdAmount, "USD", "EUR");
        double gbpAmount = converter.convert(usdAmount, "USD", "GBP");
        double kztAmount = converter.convert(usdAmount, "USD", "KZT");
        
        System.out.println("\n💱 Currency Conversions:");
        System.out.printf("💰 $%.2f USD = €%.2f EUR%n", usdAmount, eurAmount);
        System.out.printf("💰 $%.2f USD = £%.2f GBP%n", usdAmount, gbpAmount);
        System.out.printf("💰 $%.2f USD = %.2f KZT%n", usdAmount, kztAmount);
        
        CurrencyConverter.ConversionResult conversionWithFees = converter.convertWithFees(1000.0, "USD", "EUR", 2.5);
        System.out.println("\n💸 Conversion with Fees:");
        System.out.println(conversionWithFees.toString());
        
        System.out.println("\n📋 DEMO 7: Integrated Banking System");
        System.out.println("Combining all features...");
        
        Account integratedAccount = new InsuranceDecorator(
            new RewardPointsDecorator(
                new SavingsAccount(5000.0), 
                2.0
            ), 
            15000.0
        );
        
        System.out.println("🏦 Integrated Account: " + integratedAccount.getDescription());
        
        integratedAccount.deposit(1000.0);
        integratedAccount.withdraw(500.0);
        
        Transaction integratedTransaction = new Transaction(integratedAccount.getAccountNumber(), 
            Transaction.TransactionType.DEPOSIT, 1000.0, "Integrated system deposit");
        integratedTransaction.complete();
        bankLedger.addTransaction(integratedTransaction);
        
        System.out.println("\n✅ Demo completed successfully!");
        System.out.println("🎯 Patterns and Features demonstrated:");
        System.out.println("   • Decorator Pattern: Adding features to accounts");
        System.out.println("   • Facade Pattern: Simplifying complex banking operations");
        System.out.println("   • Accounting System: Transaction tracking and ledger management");
        System.out.println("   • Currency Exchange: Multi-currency conversion with fees");
        System.out.println("   • Integrated System: All features working together");
    }
}
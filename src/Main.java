import accounts.Account;
import facade.BankingFacade;
import accounting.Transaction;
import accounting.Ledger;
import currency.CurrencyConverter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Banking System Demo");
        
        BankingFacade bank = new BankingFacade();
        
        Account savings = bank.openSavings(1000);
        Account investment = bank.openInvestment(2000);
        
        bank.deposit(savings, 500);
        bank.withdraw(savings, 200);
        
        bank.deposit(investment, 1000);
        bank.withdraw(investment, 300);
        
        bank.showAccounts();
        
        Ledger ledger = new Ledger();
        ledger.addTransaction(new Transaction("SAV1", "DEPOSIT", 500, "Deposit"));
        ledger.addTransaction(new Transaction("SAV1", "WITHDRAWAL", 200, "Withdrawal"));
        
        ledger.showTransactions();
        ledger.showBalances();
        
        CurrencyConverter converter = new CurrencyConverter();
        converter.showRates();
        
        double eur = converter.convert(100, "USD", "EUR");
        System.out.println("100 USD = " + eur + " EUR");
        
        System.out.println("Demo finished");
    }
}
package accounts;

public class InvestmentAccount implements Account {
    private String accountNumber;
    private double balance;
    private static int accountCounter = 2000;

    public InvestmentAccount(double initialBalance) {
        this.accountNumber = "INV" + (++accountCounter);
        this.balance = initialBalance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Invested $" + amount + " in " + accountNumber + ". New balance: $" + balance);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from investment " + accountNumber + ". New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal from " + accountNumber);
        }
    }

    @Override
    public String getAccountType() {
        return "Investment Account";
    }

    @Override
    public String getDescription() {
        return "Investment account for trading and portfolio management";
    }
}

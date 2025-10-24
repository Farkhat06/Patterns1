package accounts;

public class SavingsAccount implements Account {
    private String accountNumber;
    private double balance;
    private static int accountCounter = 1000;

    public SavingsAccount(double initialBalance) {
        this.accountNumber = "SAV" + (++accountCounter);
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
            System.out.println("Deposited $" + amount + " to " + accountNumber + ". New balance: $" + balance);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from " + accountNumber + ". New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal from " + accountNumber);
        }
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }

    @Override
    public String getDescription() {
        return "Basic savings account with standard features";
    }
}

package accounts;

public class SavingsAccount implements Account {
    private String accountNumber;
    private double balance;
    private static int counter = 1;

    public SavingsAccount(double balance) {
        this.accountNumber = "SAV" + counter++;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Not enough money");
        }
    }
}

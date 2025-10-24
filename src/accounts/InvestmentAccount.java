package accounts;

public class InvestmentAccount implements Account {
    private String accountNumber;
    private double balance;
    private static int counter = 1;

    public InvestmentAccount(double balance) {
        this.accountNumber = "INV" + counter++;
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
        System.out.println("Invested: $" + amount);
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

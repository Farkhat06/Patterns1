package accounts;

public interface Account {
    String getAccountNumber();
    double getBalance();
    void deposit(double amount);
    void withdraw(double amount);
    String getAccountType();
    String getDescription();
}

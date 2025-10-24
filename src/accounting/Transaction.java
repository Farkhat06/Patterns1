package accounting;

public class Transaction {
    private String accountNumber;
    private String type;
    private double amount;
    private String description;
    
    public Transaction(String accountNumber, String type, double amount, String description) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    
    public String toString() {
        return type + " $" + amount + " - " + description;
    }
}

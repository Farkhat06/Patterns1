package accounting;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime timestamp;
    private String reference;
    private TransactionStatus status;
    
    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER_IN,
        TRANSFER_OUT,
        INVESTMENT,
        DIVIDEND,
        INTEREST,
        FEE,
        REFUND
    }
    
    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED,
        CANCELLED
    }
    
    public Transaction(String accountNumber, TransactionType type, double amount, String description) {
        this.transactionId = generateTransactionId();
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.reference = "";
    }
    
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getReference() { return reference; }
    public TransactionStatus getStatus() { return status; }
    
    public void setReference(String reference) { this.reference = reference; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    
    public void complete() {
        this.status = TransactionStatus.COMPLETED;
    }
    
    public void fail() {
        this.status = TransactionStatus.FAILED;
    }
    
    public void cancel() {
        this.status = TransactionStatus.CANCELLED;
    }
    
    public boolean isCompleted() {
        return status == TransactionStatus.COMPLETED;
    }
    
    public boolean isPending() {
        return status == TransactionStatus.PENDING;
    }
    
    public String getSummary() {
        String statusIcon = getStatusIcon();
        return String.format("%s %s %s - $%.2f (%s) - %s", 
            statusIcon, type, accountNumber, amount, status, description);
    }
    
    private String getStatusIcon() {
        switch (status) {
            case COMPLETED: return "✅";
            case PENDING: return "⏳";
            case FAILED: return "❌";
            case CANCELLED: return "🚫";
            default: return "❓";
        }
    }
    
    @Override
    public String toString() {
        return String.format("Transaction[%s]: %s $%.2f - %s (%s)", 
            transactionId, type, amount, description, status);
    }
}

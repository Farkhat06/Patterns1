package accounting;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Ledger {
    private List<Transaction> transactions;
    private Map<String, Double> accountBalances;
    private double totalDeposits;
    private double totalWithdrawals;
    private double totalFees;
    
    public Ledger() {
        this.transactions = new ArrayList<>();
        this.accountBalances = new HashMap<>();
        this.totalDeposits = 0;
        this.totalWithdrawals = 0;
        this.totalFees = 0;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateAccountBalance(transaction);
        updateTotals(transaction);
    }
    
    private void updateAccountBalance(Transaction transaction) {
        String accountNumber = transaction.getAccountNumber();
        double currentBalance = accountBalances.getOrDefault(accountNumber, 0.0);
        double newBalance = currentBalance;
        
        switch (transaction.getType()) {
            case DEPOSIT:
            case TRANSFER_IN:
            case DIVIDEND:
            case INTEREST:
            case REFUND:
                newBalance += transaction.getAmount();
                break;
            case WITHDRAWAL:
            case TRANSFER_OUT:
            case INVESTMENT:
            case FEE:
                newBalance -= transaction.getAmount();
                break;
        }
        
        accountBalances.put(accountNumber, newBalance);
    }
    
    private void updateTotals(Transaction transaction) {
        switch (transaction.getType()) {
            case DEPOSIT:
            case TRANSFER_IN:
            case DIVIDEND:
            case INTEREST:
            case REFUND:
                totalDeposits += transaction.getAmount();
                break;
            case WITHDRAWAL:
            case TRANSFER_OUT:
            case INVESTMENT:
                totalWithdrawals += transaction.getAmount();
                break;
            case FEE:
                totalFees += transaction.getAmount();
                break;
        }
    }
    
    public double getAccountBalance(String accountNumber) {
        return accountBalances.getOrDefault(accountNumber, 0.0);
    }
    
    public List<Transaction> getAccountTransactions(String accountNumber) {
        return transactions.stream()
            .filter(t -> t.getAccountNumber().equals(accountNumber))
            .collect(Collectors.toList());
    }
    
    public List<Transaction> getTransactionsByType(Transaction.TransactionType type) {
        return transactions.stream()
            .filter(t -> t.getType() == type)
            .collect(Collectors.toList());
    }
    
    public List<Transaction> getTransactionsByStatus(Transaction.TransactionStatus status) {
        return transactions.stream()
            .filter(t -> t.getStatus() == status)
            .collect(Collectors.toList());
    }
    
    public List<Transaction> getTransactionsInRange(LocalDateTime start, LocalDateTime end) {
        return transactions.stream()
            .filter(t -> t.getTimestamp().isAfter(start) && t.getTimestamp().isBefore(end))
            .collect(Collectors.toList());
    }
    
    public double getTotalDeposits() {
        return totalDeposits;
    }
    
    public double getTotalWithdrawals() {
        return totalWithdrawals;
    }
    
    public double getTotalFees() {
        return totalFees;
    }
    
    public double getNetBalance() {
        return totalDeposits - totalWithdrawals - totalFees;
    }
    
    public void displayAccountStatement(String accountNumber) {
        System.out.println("\n📋 Account Statement for " + accountNumber);
        System.out.println("=" .repeat(50));
        
        List<Transaction> accountTransactions = getAccountTransactions(accountNumber);
        if (accountTransactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
            return;
        }
        
        accountTransactions.sort((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()));
        
        for (Transaction transaction : accountTransactions) {
            System.out.println(transaction.getSummary());
        }
        
        System.out.println("-".repeat(30));
        System.out.printf("💰 Current Balance: $%.2f%n", getAccountBalance(accountNumber));
    }
    
    public void displayLedgerSummary() {
        System.out.println("\n📊 Ledger Summary");
        System.out.println("=" .repeat(50));
        System.out.printf("📈 Total Deposits: $%.2f%n", totalDeposits);
        System.out.printf("📉 Total Withdrawals: $%.2f%n", totalWithdrawals);
        System.out.printf("💸 Total Fees: $%.2f%n", totalFees);
        System.out.printf("💰 Net Balance: $%.2f%n", getNetBalance());
        System.out.printf("📋 Total Transactions: %d%n", transactions.size());
        
        System.out.println("\n🏦 Account Balances:");
        for (Map.Entry<String, Double> entry : accountBalances.entrySet()) {
            System.out.printf("   %s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }
    
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
    
    public int getTransactionCount() {
        return transactions.size();
    }
}

package accounting;

import java.util.*;

public class Ledger {
    private List<Transaction> transactions;
    private Map<String, Double> balances;
    
    public Ledger() {
        this.transactions = new ArrayList<>();
        this.balances = new HashMap<>();
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        String account = transaction.getAccountNumber();
        double current = balances.getOrDefault(account, 0.0);
        
        if (transaction.getType().equals("DEPOSIT")) {
            balances.put(account, current + transaction.getAmount());
        } else if (transaction.getType().equals("WITHDRAWAL")) {
            balances.put(account, current - transaction.getAmount());
        }
    }
    
    public double getBalance(String accountNumber) {
        return balances.getOrDefault(accountNumber, 0.0);
    }
    
    public void showTransactions() {
        System.out.println("Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }
    
    public void showBalances() {
        System.out.println("Balances:");
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}

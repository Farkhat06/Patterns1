package currency;

import java.util.*;

public class CurrencyConverter {
    private Map<String, Double> rates;
    
    public CurrencyConverter() {
        this.rates = new HashMap<>();
        rates.put("USD_EUR", 0.85);
        rates.put("USD_GBP", 0.73);
        rates.put("EUR_USD", 1.18);
        rates.put("GBP_USD", 1.37);
    }
    
    public double convert(double amount, String from, String to) {
        if (from.equals(to)) {
            return amount;
        }
        
        String key = from + "_" + to;
        Double rate = rates.get(key);
        if (rate != null) {
            return amount * rate;
        }
        
        System.out.println("Rate not found");
        return 0.0;
    }
    
    public void showRates() {
        System.out.println("Exchange Rates:");
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

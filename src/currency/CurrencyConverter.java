package currency;

import java.util.*;

public class CurrencyConverter {
    private Map<String, ExchangeRate> exchangeRates;
    private Set<String> supportedCurrencies;
    
    public CurrencyConverter() {
        this.exchangeRates = new HashMap<>();
        this.supportedCurrencies = new HashSet<>();
        initializeDefaultRates();
    }
    
    private void initializeDefaultRates() {
        supportedCurrencies.addAll(Arrays.asList("USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "KZT"));
        addExchangeRate("USD", "EUR", 0.85);
        addExchangeRate("USD", "GBP", 0.73);
        addExchangeRate("USD", "JPY", 110.0);
        addExchangeRate("USD", "CAD", 1.25);
        addExchangeRate("USD", "AUD", 1.35);
        addExchangeRate("USD", "CHF", 0.92);
        addExchangeRate("USD", "CNY", 6.45);
        addExchangeRate("USD", "KZT", 425.0);
        
        addExchangeRate("EUR", "USD", 1.18);
        addExchangeRate("GBP", "USD", 1.37);
        addExchangeRate("JPY", "USD", 0.0091);
        addExchangeRate("CAD", "USD", 0.80);
        addExchangeRate("AUD", "USD", 0.74);
        addExchangeRate("CHF", "USD", 1.09);
        addExchangeRate("CNY", "USD", 0.155);
        addExchangeRate("KZT", "USD", 0.00235);
    }
    
    public void addExchangeRate(String baseCurrency, String targetCurrency, double rate) {
        String key = baseCurrency + "_" + targetCurrency;
        exchangeRates.put(key, new ExchangeRate(baseCurrency, targetCurrency, rate));
    }
    
    public void updateExchangeRate(String baseCurrency, String targetCurrency, double newRate) {
        String key = baseCurrency + "_" + targetCurrency;
        ExchangeRate existingRate = exchangeRates.get(key);
        if (existingRate != null) {
            existingRate.updateRate(newRate);
        } else {
            addExchangeRate(baseCurrency, targetCurrency, newRate);
        }
    }
    
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }
        
        String directKey = fromCurrency + "_" + toCurrency;
        ExchangeRate directRate = exchangeRates.get(directKey);
        if (directRate != null) {
            return amount * directRate.getRate();
        }
        
        String reverseKey = toCurrency + "_" + fromCurrency;
        ExchangeRate reverseRate = exchangeRates.get(reverseKey);
        if (reverseRate != null) {
            return amount / reverseRate.getRate();
        }
        
        if (!fromCurrency.equals("USD") && !toCurrency.equals("USD")) {
            double usdAmount = convert(amount, fromCurrency, "USD");
            return convert(usdAmount, "USD", toCurrency);
        }
        
        System.out.println("❌ Exchange rate not found for " + fromCurrency + " to " + toCurrency);
        return 0.0;
    }
    
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }
        
        String directKey = fromCurrency + "_" + toCurrency;
        ExchangeRate directRate = exchangeRates.get(directKey);
        if (directRate != null) {
            return directRate.getRate();
        }
        
        String reverseKey = toCurrency + "_" + fromCurrency;
        ExchangeRate reverseRate = exchangeRates.get(reverseKey);
        if (reverseRate != null) {
            return 1.0 / reverseRate.getRate();
        }
        
        return 0.0;
    }
    
    public boolean isCurrencySupported(String currency) {
        return supportedCurrencies.contains(currency);
    }
    
    public Set<String> getSupportedCurrencies() {
        return new HashSet<>(supportedCurrencies);
    }
    
    public void displayExchangeRates(String baseCurrency) {
        System.out.println("\n💱 Exchange Rates for " + baseCurrency);
        System.out.println("=" .repeat(50));
        
        for (String targetCurrency : supportedCurrencies) {
            if (!targetCurrency.equals(baseCurrency)) {
                double rate = getExchangeRate(baseCurrency, targetCurrency);
                if (rate > 0) {
                    System.out.printf("💰 %s → %s: %.4f%n", baseCurrency, targetCurrency, rate);
                }
            }
        }
    }
    
    public void displayAllExchangeRates() {
        System.out.println("\n💱 All Exchange Rates");
        System.out.println("=" .repeat(50));
        
        for (ExchangeRate rate : exchangeRates.values()) {
            System.out.println(rate.toString());
        }
    }
    
    public ConversionResult convertWithFees(double amount, String fromCurrency, String toCurrency, double feePercent) {
        double convertedAmount = convert(amount, fromCurrency, toCurrency);
        double fee = convertedAmount * (feePercent / 100);
        double finalAmount = convertedAmount - fee;
        
        return new ConversionResult(amount, fromCurrency, finalAmount, toCurrency, fee, feePercent);
    }
    
    public static class ConversionResult {
        private double originalAmount;
        private String originalCurrency;
        private double convertedAmount;
        private String targetCurrency;
        private double fee;
        private double feePercent;
        
        public ConversionResult(double originalAmount, String originalCurrency, 
                              double convertedAmount, String targetCurrency, 
                              double fee, double feePercent) {
            this.originalAmount = originalAmount;
            this.originalCurrency = originalCurrency;
            this.convertedAmount = convertedAmount;
            this.targetCurrency = targetCurrency;
            this.fee = fee;
            this.feePercent = feePercent;
        }
        
        public double getOriginalAmount() { return originalAmount; }
        public String getOriginalCurrency() { return originalCurrency; }
        public double getConvertedAmount() { return convertedAmount; }
        public String getTargetCurrency() { return targetCurrency; }
        public double getFee() { return fee; }
        public double getFeePercent() { return feePercent; }
        
        @Override
        public String toString() {
            return String.format("💱 %s %.2f → %s %.2f (Fee: %.2f %s %.2f%%)", 
                originalCurrency, originalAmount, targetCurrency, convertedAmount, 
                targetCurrency, fee, feePercent);
        }
    }
}

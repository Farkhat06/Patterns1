package currency;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRate {
    private String baseCurrency;
    private String targetCurrency;
    private double rate;
    private LocalDateTime lastUpdated;
    private double previousRate;
    
    public ExchangeRate(String baseCurrency, String targetCurrency, double rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
        this.previousRate = rate;
        this.lastUpdated = LocalDateTime.now();
    }
    
    public String getBaseCurrency() { return baseCurrency; }
    public String getTargetCurrency() { return targetCurrency; }
    public double getRate() { return rate; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public double getPreviousRate() { return previousRate; }
    
    public void updateRate(double newRate) {
        this.previousRate = this.rate;
        this.rate = newRate;
        this.lastUpdated = LocalDateTime.now();
    }
    
    public double getRateChange() {
        return rate - previousRate;
    }
    
    public double getRateChangePercent() {
        if (previousRate == 0) return 0;
        return (getRateChange() / previousRate) * 100;
    }
    
    public boolean isRateUp() {
        return getRateChange() > 0;
    }
    
    public boolean isRateDown() {
        return getRateChange() < 0;
    }
    
    @Override
    public String toString() {
        String changeSymbol = isRateUp() ? "📈" : isRateDown() ? "📉" : "➡️";
        return String.format("%s %s/%s: %.4f %s%.4f (%.2f%%)", 
            changeSymbol, baseCurrency, targetCurrency, rate, 
            isRateUp() ? "+" : "", getRateChange(), getRateChangePercent());
    }
}

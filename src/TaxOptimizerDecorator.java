public class TaxOptimizerDecorator extends AccountDecorator {
    public TaxOptimizerDecorator(Account account) {
        super(account);
    }

    public void optimizeTaxes() {
        System.out.println("Taxes optimized for lower savings.");
    }
}

public class InsuranceDecorator extends AccountDecorator {
    public InsuranceDecorator(Account account) {
        super(account);
    }

    public void addInsurance() {
        System.out.println("Insurance benefits added to the account.");
    }
}

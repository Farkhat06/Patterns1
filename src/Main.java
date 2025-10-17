public class Main {
    public static void main(String[] args) {
        BankingFacade facade = new BankingFacade();

        Account savingsAccount = facade.openAccountWithBenefits();
        savingsAccount.deposit(1000);
        System.out.println("Savings Account Balance: " + savingsAccount.getBalance());

        Account investmentAccount = facade.investWithSafetyMode();
        investmentAccount.deposit(2000);
        System.out.println("Investment Account Balance: " + investmentAccount.getBalance());

        facade.closeAccount(savingsAccount);
        facade.closeAccount(investmentAccount);
    }
}

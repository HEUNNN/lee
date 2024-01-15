package access;

public class BankAccountMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.deposit(100000);
        account.withdraw(30000);
        System.out.println("balance = " + account.getBalance());
    }
}

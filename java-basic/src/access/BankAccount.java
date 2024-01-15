package access;

public class BankAccount {
    /*
    balance: 데이터 필드는 외부에 직접 노출하지 않는다. BankAccount 클래스가 제공하는 getBalance() 메서드를 통해서만 접근할 수 있다.
    isAmountValue(): 입력 금액을 검증하는 기능은 내부에서만 필요한 기능으로, private를 사용하였다.
     */
    private int balance;

    public BankAccount() {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    // public 메서드: deposit
    public void deposit(int amount) {
        if (isAmountValid(amount)) {
            balance += amount;
        } else {
            System.out.println("유효하지 않은 금액입니다.");
        }
    }

    // public 메서드: withdraw
    public void withdraw(int amount) {
        if (isAmountValid(amount) && balance - amount >= 0) {
            amount -= amount;
        } else {
            System.out.println("유효하지 않은 금액입니다.");
        }
    }

    private boolean isAmountValid(int amount) {
        // 금액이 0보다 큰지 확인
        return amount > 0;
    }
}

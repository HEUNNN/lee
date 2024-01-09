package oop1.ex;

public class Account {
    public int balance; // 잔액

    public void deposit(int amount) {
        // 입금
        this.balance += amount;
    }

    public void withdraw(int amount) {
        // 출금
        if (this.balance - amount < 0) {
            System.out.println("잔액 부족");
        } else {
            this.balance -= amount;
        }
    }

    public void checkBalance() {
        System.out.println("잔고: " + this.balance);
    }
}

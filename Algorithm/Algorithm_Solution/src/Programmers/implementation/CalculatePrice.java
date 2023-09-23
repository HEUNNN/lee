package Programmers.implementation;

public class CalculatePrice {
    // https://school.programmers.co.kr/learn/courses/30/lessons/82612

    public static void main(String[] args) {
        long solution = new CalculatePrice().solution(3, 20, 4);
        System.out.println(solution);
    }

    public long solution(int price, int money, int count) {
        long calculatedPrice = calPrice(price, count);
        if (money >= calculatedPrice) return 0;
        return calculatedPrice - money;
    }

    private long calPrice(int price, int count) {
        long p = 0;
        for (int i = 1; i <= count; i++) {
            p += (price * i * 1.0);
        }
        return p;
    }
}

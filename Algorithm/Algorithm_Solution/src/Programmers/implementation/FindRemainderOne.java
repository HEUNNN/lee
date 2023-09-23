package Programmers.implementation;

public class FindRemainderOne {
    // https://school.programmers.co.kr/learn/courses/30/lessons/87389
    public static void main(String[] args) {
        int solution = new FindRemainderOne().solution(12);
        System.out.println(solution);
    }
    public int solution(int n) {
        int num = 2;
        while ((n % num) != 1) {
            num++;
        }

        return num;
    }
}

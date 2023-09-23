package Programmers.implementation;

public class MaxAndMin {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12939
    public static void main(String[] args) {
        String s = "1 2 3 4";
        String result = new MaxAndMin().solution(s);
        System.out.println(result);
    }
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] numbers = s.split(" ");

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (String number : numbers) {
            max = Math.max(max, Integer.parseInt(number));
            min = Math.min(min, Integer.parseInt(number));
        }
        sb.append(min).append(" ").append(max);
        return sb.toString();
    }
}

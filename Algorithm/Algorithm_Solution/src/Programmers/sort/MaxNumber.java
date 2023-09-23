package Programmers.sort;

import java.util.*;

public class MaxNumber {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42746
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        String solution = new MaxNumber().solution(numbers);
        System.out.println(solution);
    }
    public String solution(int[] numbers) {
        String answer;
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int n : numbers) {
            list.add(String.valueOf(n));
        }
        list.sort((x, y) -> (y + x).compareTo(x + y));
        for (String s : list) {
            sb.append(s);
        }
        answer = sb.toString();
        if (answer.startsWith("0")) return "0";
        return answer;
    }
}

package Programmers.implementation;

import java.util.*;

public class Budget {
    public static void main(String[] args) {
        int[] d = {9, 10};
        int solution = new Budget().solution(d, 4);
        System.out.println(solution);
    }
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        for (int i = 0; i < d.length; i++) {
            budget -= d[i];
            if (budget < 0) return answer = i;
        }
        return answer = d.length;
    }
}

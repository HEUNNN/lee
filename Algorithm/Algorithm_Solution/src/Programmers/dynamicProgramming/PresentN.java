package Programmers.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PresentN {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42895
    public static void main(String[] args) {
        int N = 2;
        int number = 11;
        int solution = new PresentN().solution(N, number);
        System.out.println(solution);
    }

    public int solution(int N, int number) {
        int answer = -1;
        List<Set<Integer>> dp = initList();
        dp.get(1).add(N);
        fillDp(N, dp);
        for (int i = 1; i <= 8; i++) {
            for (Integer num : dp.get(i)) {
                if (num.equals(number)) return i;
            }
        }
        return answer;
    }

    private List<Set<Integer>> initList() {
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(new HashSet<>());
        }
        return list;
    }

    private void fillDp(int N, List<Set<Integer>> dp) {
        for (int i = 2; i <= 8; i++) {
            Set<Integer> countTarget = dp.get(i);
            for (int j = 1; j <= i; j++) {
                Set<Integer> prevSet = dp.get(j);
                Set<Integer> postSet = dp.get(i - j);
                for (Integer prev : prevSet) {
                    for (Integer post : postSet) {
                        countTarget.add(prev + post);
                        countTarget.add(prev - post);
                        countTarget.add(prev * post);
                        if (prev != 0 && post != 0) {
                            countTarget.add(prev / post);
                        }
                    }
                }
            }
            countTarget.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
    }
}

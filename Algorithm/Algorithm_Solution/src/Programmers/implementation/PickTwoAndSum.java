package Programmers.implementation;

import java.util.*;

public class PickTwoAndSum {
    // https://school.programmers.co.kr/learn/courses/30/lessons/68644
    // 두 개 뽑아서 더하기
    public static void main(String[] args) {
        int[] numbers = {2, 1, 3, 4, 1};
        int[] result = new PickTwoAndSum().solution2(numbers);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public int[] solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        int[] answer = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int idx = 0;
        while (iterator.hasNext()) {
            answer[idx] = iterator.next();
            idx++;
        }
        Arrays.sort(answer);

        return answer;
    }

    public int[] solution2(int[] numbers) { // 시간 오래 걸림
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}

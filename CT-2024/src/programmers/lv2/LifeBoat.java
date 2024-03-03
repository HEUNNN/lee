package programmers.lv2;

import java.util.Arrays;

public class LifeBoat {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42885
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
//        int[] people = {40, 40, 40};
//        int[] people = {70, 80, 50};
//        int[] people = {100, 40, 40, 40};
        int limit = 100;
        int answer = getLifeBoarCnt2(people, limit);
        System.out.println(answer);
    }

    public static int getLifeBoatCnt1(int[] people, int limit) {
        // 효율설 검사 실패 -> o(n^2)
        int answer = 0;
        boolean[] saved = new boolean[people.length];
        Arrays.sort(people);
        Arrays.fill(saved, false);

        for (int i = people.length - 1; i >= 0; i--) {
            if (saved[i]) continue;
            answer++;
            saved[i] = true;
            for (int j = 0; j < i; j++) {
                if (saved[j]) continue;
                if (limit - people[i] >= people[j]) {
                    saved[j] = true;
                    break;
                }
            }
        }
        return answer;
    }

    public static int getLifeBoarCnt2(int[] people, int limit) {
        int answer = 0;
        int minIdx = 0;
        int maxIdx = people.length - 1;
        Arrays.sort(people);

        while (minIdx <= maxIdx) {
            if (minIdx == maxIdx) {
                answer++;
                break;
            }
            if (people[maxIdx] + people[minIdx] > limit) {
                maxIdx--;
            } else {
                maxIdx--;
                minIdx++;
            }
            answer++;
        }
        return answer;
    }
}

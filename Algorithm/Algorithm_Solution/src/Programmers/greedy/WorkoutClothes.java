package Programmers.greedy;

import java.util.*;

public class WorkoutClothes {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42862/solution_groups?language=java&type=all
    static int borrowCnt = 0;

    public int solution(int n, int[] lost, int[] reserve) {
        Map<Integer, Boolean> lostMap = new HashMap<>();
        Map<Integer, Boolean> reserveMap = new HashMap<>(); // reserve, 여분의 체육복을 사용했는지 아닌지를 작성해두는 map
        mapInit(reserveMap, lostMap, reserve, lost);
        for (Integer l : lostMap.keySet()) {
            if (!lostMap.get(l) && reserveMap.getOrDefault(l - 1, false)) {
                reserveMap.put(l - 1, false);
                lostMap.put(l, true);
                borrowCnt++;
            } else if (!lostMap.get(l) && reserveMap.getOrDefault(l + 1, false)) {
                reserveMap.put(l + 1, false);
                lostMap.put(l, true); // 잃어버린 학생에게 체육복을 빌려줬으면 더 이상 빌려줄 필요가 없다는 의미로 true로 변경
                borrowCnt++;
            }
        }

        return n - (lost.length - borrowCnt);
    }

    public int solution2(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] people = new int[n];

        // 중복 처리
        for (int l : lost) {
            people[l - 1]--;
        }
        for (int r : reserve) {
            people[r - 1]++; // 인덱스는 0부터 시작하기 때문에 -1
        }

        for (int i = 0; i < n; i++) {
            if (people[i] == -1) {
                if (i - 1 >= 0 && people[i - 1] == 1) {
                    people[i]++;
                    people[i - 1]--;
                } else if (i + 1 < n && people[i + 1] == 1) {
                    people[i]++;
                    people[i + 1]--;
                } else {
                    answer--;
                }
            }
        }
        return answer;
    }

    private void mapInit(Map<Integer, Boolean> reserveMap, Map<Integer, Boolean> lostMap, int[] reserve, int[] lost) {

        for (int i = 0; i < lost.length; i++) {
            lostMap.put(lost[i], false);
        }
        for (int i = 0; i < reserve.length; i++) {
            if (lostMap.containsKey(reserve[i])) {
                lostMap.put(reserve[i], true); // 여분이 있는 학생인데, 도난을 당한 경우 자신의 여분 체육복을 사용하면 되기 때문에 더 이상 빌려줄 필요가 없다는 의미의 true로 변경
                borrowCnt++; // 중복 제거
            } else {
                reserveMap.put(reserve[i], true); // 아직 여분의 체육복을 빌려주지 않은 상태 true
            }
        }
    }
}

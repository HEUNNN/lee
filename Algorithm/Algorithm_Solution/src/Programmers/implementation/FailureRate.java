package Programmers.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FailureRate {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42889
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stages2 = {4, 4, 4, 4};
        new FailureRate().solution(N, stages2);
    }

    public int[] solution(int N, int[] stages) {
        int usersNum = stages.length;
        int[] stageCount = new int[N + 1];
        for (int stage : stages) {
            if (stage == N + 1) continue;
            stageCount[stage]++;
        }

        int tmpUsersNum = usersNum;
        List<Stage> stageList = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            double failureRate = (double) stageCount[i] / tmpUsersNum;
            tmpUsersNum -= stageCount[i];
            stageList.add(new Stage(i, failureRate));
        }
        Collections.sort(stageList, Collections.reverseOrder());
        int[] answer = new int[stageList.size()];
        for (int i = 0; i < stageList.size(); i++) {
            answer[i] = stageList.get(i).stageNum;
        }

        return answer;
    }

    class Stage implements Comparable<Stage> {
        public int stageNum;
        public double failure;

        public Stage(int stageNum, double failure) {
            this.stageNum = stageNum;
            this.failure = failure;
        }

        @Override
        public int compareTo(Stage o) {
            if (failure < o.failure) { // failure가 현재 비교 대상
                return -1;
            } else if (failure > o.failure) {
                return 1;
            } else {
                if (stageNum < o.stageNum) {
                    return 1;
                }
                return -1;
            }
        }
    }

}

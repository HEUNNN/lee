package Programmers.lv1;

import java.util.*;

public class RunningRace {

    // https://school.programmers.co.kr/learn/courses/30/lessons/178871
    public static void main(String[] args) {
        RunningRace runningRace = new RunningRace();
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        String[] result = runningRace.solution(players, callings);
        for (String r : result) {
            System.out.println(r);
        }
    }

    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> nameToIdx = getNameToIdx(players);

        for (String calling : callings) {
            int idx1 = nameToIdx.get(calling);
            if (idx1 > 0) {
                swap(players, idx1, idx1 - 1);
                nameToIdx.put(players[idx1 - 1], idx1 - 1);
                nameToIdx.put(players[idx1], idx1);
            }
        }
        return players;
    }

    private Map<String, Integer> getNameToIdx(String[] players) {
        Map<String, Integer> nameToIdx = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            nameToIdx.put(players[i], i);
        }
        return nameToIdx;
    }

    private void swap(String[] players, int idx1, int idx2) {
        String tmpName = players[idx1];
        players[idx1] = players[idx2];
        players[idx2] = tmpName;
    }

}

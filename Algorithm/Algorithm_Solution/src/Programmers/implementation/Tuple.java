package Programmers.implementation;

import java.util.*;

public class Tuple {
    // https://school.programmers.co.kr/learn/courses/30/lessons/64065
    public int[] solution(String s) {
        String[][] strings = arrayInit(s);
        if (strings.length == 1) {
            return new int[] {Integer.parseInt(strings[0][0])};
        }
        Map<Integer, Integer> numToCount = initMap(strings);

        int[] answer = new int[strings.length];
        for (int i = strings.length - 1; i >= 0; i--) {
            for (Integer key : numToCount.keySet()) {
                if (numToCount.get(key) == (i + 1)) {
                    answer[strings.length - 1 - i] = key;
                    numToCount.remove(key);
                    break;
                }
            }
        }
        return answer;
    }

    private String[][] arrayInit(String s) {
        String[] split = s.substring(2, s.length() - 2).split("},");
        String[][] arr = new String[split.length][];

        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("{")) {
                split[i] = split[i].substring(1);
            }
            String[] subStrArr = split[i].split(",");
            arr[i] = subStrArr;
        }

        return arr;
    }

    private Map<Integer, Integer> initMap(String[][] strings) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                int target = Integer.parseInt(strings[i][j]);
                if (numToCount.containsKey(target)) {
                    numToCount.put(target, numToCount.get(target) + 1);
                } else {
                    numToCount.put(target, 1);
                }
            }
        }
        return numToCount;
    }
}

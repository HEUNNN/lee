package Programmers.hash;

import java.util.*;

public class Camouflage {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42578
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String c = clothes[i][1];
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        return answer - 1;
    }
}

package Programmers.lv1;

import java.util.HashMap;
import java.util.Map;

public class MemoriesScore {

    // https://school.programmers.co.kr/learn/courses/30/lessons/176963

    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {{"may", "kein", "kain", "radi"}, {"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}};

        int[] solution = new MemoriesScore().solution(name, yearning, photo);

        for (int i : solution) {
            System.out.println(i);
        }
    }

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> memoriesMap = getMemoriesMap(name, yearning);

        for (int i = 0; i < photo.length; i++) {
            int score = 0;
            for (String person : photo[i]) {
                if (memoriesMap.containsKey(person)) {
                    score += memoriesMap.get(person);
                }
            }
            answer[i] = score;
        }

        return answer;
    }

    private Map<String, Integer> getMemoriesMap(String[] name, int[] yearning) {
        Map<String, Integer> memoriesMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            memoriesMap.put(name[i], yearning[i]);
        }
        return memoriesMap;
    }
}

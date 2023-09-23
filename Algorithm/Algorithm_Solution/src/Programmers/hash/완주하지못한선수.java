package Programmers.hash;

import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {

    public String getNotCompletePlayer(String[] participant, String[] completion) {
        String notComplete = null;
        Map<String, Integer> participantMap = new HashMap<>();

        for (String p : participant) {
            participantMap.put(p, participantMap.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            participantMap.put(c, participantMap.getOrDefault(c, 0) - 1);
        }

        for (String p : participantMap.keySet()) {
            if (participantMap.get(p) != 0) {
                notComplete = p;
            }
        }

        return notComplete;
    }
}

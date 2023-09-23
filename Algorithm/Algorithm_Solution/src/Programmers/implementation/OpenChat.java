package Programmers.implementation;

import java.util.*;

public class OpenChat {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42888
    public static void main(String[] args) {
        String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] solution = new OpenChat().solution(record);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    public String[] solution(String[] record) {

        String enterMsg = "님이 들어왔습니다.";
        String leaveMsg = "님이 나갔습니다.";

        Map<String, String> idToNickname = new HashMap<>();
        fillNickname(record, idToNickname);
        ArrayList<String> answer = new ArrayList<>();


        for (int i = 0; i < record.length; i++) {
            String[] split = record[i].split(" ");
            if (split[0].equals("Enter")) {
                answer.add(idToNickname.get(split[1]) + enterMsg);
            } else if (split[0].equals("Leave")) {
                answer.add(idToNickname.get(split[1]) + leaveMsg);
            }
        }
        return answer.toArray(String[]::new);
    }

    private void fillNickname(String[] record, Map<String, String> idToNickname) { // key: userId, value: nickname 채우는 메서드
        for (int i = 0; i < record.length; i++) {
            String[] s = record[i].split(" ");
            if (s.length == 3) {
                String id = s[1];
                idToNickname.put(id, s[2]);
            }
        }
    }

}

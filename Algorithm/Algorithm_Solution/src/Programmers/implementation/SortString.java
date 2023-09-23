package Programmers.implementation;

import java.util.*;

public class SortString {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12915
    // 문자열 내 마음대로 정렬하기
    public static void main(String[] args) {
        String[] strings1 = {"sun", "bed", "car"};
        String[] strings2 = {"cdx", "abce", "abcd"};
        String[] solution = new SortString().solution(strings2, 2);
        for (String s : solution) {
            System.out.println(s);
        }
    }
    // 다른 사람의 풀이 → Arrays.sort Overriding
    public String[] solution(String[] strings, int n) {

        Arrays.sort(strings, (s1, s2) -> {
            if (s1.charAt(n) > s2.charAt(n)) return 1; // s2가 우선으로 정렬됨
            else if (s1.charAt(n) == s2.charAt(n)) return s1.compareTo(s2); // compareTo는 사전순으로 정렬한다.
            else return -1;
        });

        return strings;
    }

    // 나의 풀이
    public String[] solution2(String[] strings, int n) {
        String[] answer = new String[strings.length];

        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.charAt(n) > o2.charAt(n)) return 1;
            else if (o1.charAt(n) == o2.charAt(n)) {
                return check(o1, o2);
            } else {
                return -1;
            }
        });

        for (String string : strings) {
            pq.add(string);
        }

        for (int i = 0; i < strings.length; i++) {
            answer[i] = pq.poll();
        }

        return answer;
    }

    private int check(String str1, String str2) {
        if (str1.length() > str2.length()) {
            for (int i = 0; i < str2.length(); i++) {
                if (str1.charAt(i) > str2.charAt(i)) return 1;
                else if (str1.charAt(i) < str2.charAt(i)) return -1;
            }
        } else {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) > str2.charAt(i)) return 1;
                else if (str1.charAt(i) < str2.charAt(i)) return -1;
            }
        }
        return 1;
    }
}

package Programmers.implementation;

import java.util.*;

public class SortStringDescendingOrder {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12917
    public static void main(String[] args) {
        String s = "Zbcdefg";
        String solution = new SortStringDescendingOrder().solution(s);
        System.out.println(solution);
    }

    public String solution(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars); // char[]은 comparator 사용 못함
        return new StringBuilder(new String(chars)).reverse().toString();

        // new String()에 char[]를 전달해주면 char[] 원소 하나하나를 모두 합친 String이 된다.
        // StringBuilder 설명
        // Stirng은 변경 불가능한 문자열을 생성하지만 StringBuilder는 변경 가능한 문자열을 만들어 주기 때문에, String을 합치는 작업 시 발생하는 성능문제를 해결하는 대안이 될 수 있다.
        // https://onlyfor-me-blog.tistory.com/317
    }
}

package Programmers.implementation;

import java.util.*;

public class ReverseTernary {
    // https://school.programmers.co.kr/learn/courses/30/lessons/68935
    // 3진법 뒤집기
    public static void main(String[] args) {
        int result = new ReverseTernary().solution2(45);
        System.out.println(result);
    }

    public int solution(int n) {
        if (n == 1) return 1;
        int answer = 0;
        double max = 1;
        double pow = 0;
        while (max < n) {
            max = Math.pow(3, pow);
            pow++;
        }
        if (max != n) {
            max /= 3;
            pow -= 1; // 3진법으로 표현했을 때 길이가 됨
        }

        Stack<Integer> st = new Stack<>();
        int tmp = (int) max;
        for (int i = 0; i < pow; i++) {
            st.add(n / tmp);
            n %= tmp;
            tmp /= 3;
        }

        while (!st.isEmpty()) {
            Integer num = st.pop();
            answer += ((int) max * num);
            max /= 3;
        }

        return answer;
    }

    public int solution2(int n) {
        String a = "";
        while (n > 0) {
            a = a + (n % 3);
            n /= 3;
        }
        return Integer.parseInt(a, 3);
    }
}

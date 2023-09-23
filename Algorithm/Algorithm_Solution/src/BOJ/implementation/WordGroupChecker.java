package BOJ.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordGroupChecker {
    // https://www.acmicpc.net/problem/1316
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            if (checker(br.readLine())) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static boolean checker(String str) {
        List<Character> usedStr = new ArrayList<>();
        for (int i = 1; i < str.length(); i++) {
            if (usedStr.contains(str.charAt(i))) return false;
            if (str.charAt(i - 1) != str.charAt(i)) {
                usedStr.add(str.charAt(i - 1));
            }
        }
        return true;
    }
}

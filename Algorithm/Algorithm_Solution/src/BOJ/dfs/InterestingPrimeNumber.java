package BOJ.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InterestingPrimeNumber {
    // https://www.acmicpc.net/problem/2023
    private static int N;
    private static int[] startNumberCandidate = {2, 3, 5, 7};
    private static int[] nextNumberCandidate = {1, 3, 5, 7, 9};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 자릿수 N
        for (int startNumber : startNumberCandidate) {
            dfs(startNumber, 1);
        }
    }

    private static void dfs(int number, int jarisu) {
        if (jarisu == N) {
            if (isPrimeNumber(number)) {
                System.out.println(number);
            }
            return;
        }

        for (int nextNumber : nextNumberCandidate) {
            if (isPrimeNumber(number * 10 + nextNumber)) {
                dfs(number * 10 + nextNumber, jarisu + 1);
            }
        }
    }

    private static boolean isPrimeNumber(int number) {
        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

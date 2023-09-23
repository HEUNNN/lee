package BOJ.intervalSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FireFly {
    // https://www.acmicpc.net/problem/3020
    static int n;
    static int h;
    static int[] topSection;
    static int[] bottomSection;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        topSection = new int[h + 1];
        bottomSection = new int[h + 1];
        for (int i = 0; i < n; i++) {
            int section = Integer.parseInt(br.readLine());
            int i1 = i % 2 == 0 ? (bottomSection[section]++) : (topSection[h - section + 1]++);
        }


        // bottom 구간 합
        for (int i = h - 1; i >= 1; i--) {
            bottomSection[i] += bottomSection[i + 1];
        }
        // top 구간 합
        for (int i = 1; i <= h; i++) {
            topSection[i] += topSection[i - 1];
        }
        int count = 0;
        for (int i = 1; i <= h; i++) {
            int total = bottomSection[i] + topSection[i];
            if (min > total) {
                count = 1;
                min = total;
            } else if (min == total) {
                count++;
            }
        }
        System.out.println(min + " " + count);

    }

}
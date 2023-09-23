package sw_expert.d3;

import java.io.*;
import java.util.StringTokenizer;


class FlattenV2 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 10;


        for (int test_case = 1; test_case <= T; test_case++) {
            int dumpLimit = Integer.parseInt(br.readLine());
            int[] boxes = new int[101];
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                int box = Integer.parseInt(st.nextToken());
                boxes[box]++;
                max = Math.max(max, box);
                min = Math.min(min, box);
            }
            int result = flattening(min, max, dumpLimit, boxes);
            System.out.println("#" + test_case + " " + result);
        }
    }

    public static int flattening(int min, int max, int dumpLimit, int[] boxes) {
        while ((max - min) > 1 && dumpLimit > 0) {
            boxes[min]--;
            boxes[min + 1]++;
            boxes[max]--;
            boxes[max - 1]++;
            max -= (boxes[max] == 0) ? 1 : 0;
            min += (boxes[min] == 0) ? 1 : 0;
            dumpLimit--;
        }
        return max - min;
    }
}
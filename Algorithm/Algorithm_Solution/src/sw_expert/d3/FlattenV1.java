package sw_expert.d3;

import java.util.Arrays;
import java.util.Scanner;


class FlattenV1 {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = 10;


        for (int test_case = 1; test_case <= T; test_case++) {
            int dumpLimit = sc.nextInt();
            int[] boxes = new int[100];
            for (int i = 0; i < 100; i++) {
                boxes[i] = sc.nextInt();
            }
            int result = flattening(dumpLimit, boxes);
            System.out.println("#" + test_case + " " + result);
        }
    }

    public static int flattening(int dumpLimit, int[] boxes) {
        Arrays.sort(boxes);
        while ((boxes[99] - boxes[0]) > 1 && dumpLimit > 0) {
            boxes[0]++;
            boxes[99]--;
            dumpLimit--;
            Arrays.sort(boxes);
        }
        return boxes[99] - boxes[0];
    }
}
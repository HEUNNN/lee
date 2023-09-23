package sw_expert.d3;

import java.util.Scanner;

class AlphabetStudy {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            char[] chars = sc.next().toCharArray();
            int cnt = 1;
            if (chars[0] != 'a') {
                System.out.println("#" + test_case + " 0");
                continue;
            }
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] < 97 || chars[i] > 122) continue;
                if (chars[i] - chars[i - 1] == 1) {
                    cnt++;
                } else break;
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}
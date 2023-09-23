package sw_expert.d2;

import java.util.Scanner;

public class PatternPartLength {
    // https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5P1kNKAl8DFAUq&categoryId=AV5P1kNKAl8DFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=2&pageSize=10&pageIndex=1
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            String strings = sc.next();
            int min = 10;
            for (int j = 10; j >= 2; j--) {
                String check = strings.substring(0, j);
                if (strings.substring(j).startsWith(check)) {
                    min = Math.min(min, j);
                }
            }
            System.out.printf("#%d %d\n", i, min);
        }
    }
}
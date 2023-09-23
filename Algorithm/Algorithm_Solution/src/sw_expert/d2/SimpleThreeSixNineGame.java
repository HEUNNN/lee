package sw_expert.d2;

import java.util.Scanner;

public class SimpleThreeSixNineGame {
    // https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5PTeo6AHUDFAUq&categoryId=AV5PTeo6AHUDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=2&pageSize=10&pageIndex=1
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int count = 0;
            String strNumber = String.valueOf(i);
            for (int j = 0; j < strNumber.length(); j++) {
                char target = strNumber.charAt(j);
                if (target == '3' || target == '6' || target == '9') count++;
            }
            if (count == 0) {
                System.out.print(i + " ");
            } else {
                String printStr = "";
                for (int k = 0; k < count; k++) {
                    printStr += "-";
                }
                System.out.print(printStr + " ");
            }
        }
    }
}

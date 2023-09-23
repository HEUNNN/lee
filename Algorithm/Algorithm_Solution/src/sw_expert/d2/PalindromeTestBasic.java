package sw_expert.d2;


import java.util.Scanner;

class PalindromeTestBasic {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String str = sc.next();
            int n = str.length();
            int result = 1;
            for (int i = 0; i < n; i++) {
                if (str.charAt(i) != str.charAt(n - 1 - i)) {
                    result = 0;
                    break;
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}
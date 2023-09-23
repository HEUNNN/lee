package sw_expert.d2;


import java.util.Scanner;

class PascalTriangle {
    //https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5P0-h6Ak4DFAUq&categoryId=AV5P0-h6Ak4DFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=2&pageSize=10&pageIndex=1
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            arr[0][0] = 1;
            for (int i = 1; i < n; i++) {
                arr[i][0] = 1;
                arr[i][i] = 1;
                for (int j = 1; j < i; j++) {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
            }
            System.out.println("#" + test_case);
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
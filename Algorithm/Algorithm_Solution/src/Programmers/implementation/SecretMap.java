package Programmers.implementation;

public class SecretMap {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17681
    // 나의 풀이, 시간 오래 걸림
    static String str = "";

    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] solution = new SecretMap().solution(n, arr1, arr2);
        for (String s : solution) {
            System.out.println(s);
        }

    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] str1 = new String[n];
        String[] str2 = new String[n];
        initStrArr(str1, arr1);
        initStrArr(str2, arr2);

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                char c1 = str1[i].charAt(j);
                char c2 = str2[i].charAt(j);
                if (c1 == '1' || c2 == '1') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }

    private void initStrArr(String[] strArr, int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            makeBinary(arr[i], n, 0);
            strArr[i] = str;
            str = "";
        }
    }

    private void makeBinary(int number, int n, int cnt) { // 이진수 최대 크기: n
        if (n == cnt) return;
        makeBinary(number / 2, n, cnt + 1);
        str += number % 2;
    }
}

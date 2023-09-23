package Programmers.bit;

public class SecretMap2 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17681
    public static void main(String[] args) {
        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] solution = new SecretMap2().solution(n, arr1, arr2);
        for (String s : solution) {
            System.out.println(s);
        }

    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[] secretMap = new int[n];
        for (int i = 0; i < n; i++) {
            // 16 8 4 2 1
            secretMap[i] = arr1[i] | arr2[i]; // 비트 단위 or 연산
            answer[i] = makeSharp(n, secretMap[i]);
        }

        return answer;
    }

    private String makeSharp(int n, int number) {
        if (number == 0) {
            if (n > 0) { // 0채워넣기 즉, " " 채워 넣기
                String tmp = "";
                for (int i = 0; i < n; i++) {
                    tmp += " ";
                }
                return tmp;
            } else {
                return "";
            }
        } else {
            return number % 2 == 0 ? makeSharp(n - 1, number / 2) + " " : makeSharp(n - 1, number / 2) + "#";
        }
    }
}

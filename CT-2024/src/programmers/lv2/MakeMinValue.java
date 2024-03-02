package programmers.lv2;

import java.util.Arrays;

public class MakeMinValue {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12941
    public static void main(String[] args) {
//        int[] A = {1, 4, 2};
//        int[] B = {5, 4, 4};

        int[] A = {1, 2};
        int[] B = {3, 4};

        long answer = new MakeMinValue().makeMinValue(A, B);
        System.out.println(answer);

    }

    public long makeMinValue(int[] A, int[] B) {
        int len = A.length - 1;

        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0;

        for (int i = 0; i < len; i++) {
            int j = len - i;
            sum += A[i] * B[j];
        }

        return sum;
    }
}

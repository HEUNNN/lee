package programmers.lv2;

public class NextBiggerNumber {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12911
    public static void main(String[] args) {
        int n = 15;
        int[] nResult = getBinaryStringResult(n);
        int max = 1000000;
        int answer = 0;
        for (int i = n + 1; i <= max; i++) {
            int[] result = getBinaryStringResult(i);
            if (result[1] == nResult[1]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    public static int[] getBinaryStringResult(int number) {
        // 숫자와 2진수 변환 후 1의 개수 배열 반환
        int[] ret = new int[2];
        ret[0] = number;
        while (true) {
            if (number == 1) {
                ret[1]++;
                break;
            }
            ret[1] += number % 2;
            number /= 2;
        }
        return ret;
    }
}

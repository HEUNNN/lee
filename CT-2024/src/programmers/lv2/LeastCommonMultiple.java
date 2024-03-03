package programmers.lv2;

public class LeastCommonMultiple {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12953
    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 14};
        int answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
            answer = LCM(answer, arr[i]);
        }
        System.out.println(answer);

    }

    // 최소 공배수(LCM) ->  주어진 두 수를 곱한 다음 두 수의 최대 공약수로 나누어 구할 수 있다.
    public static int LCM(int a, int b) {
        if (a == b) {
            return 1;
        }
        return (a * b) / GCD(Math.max(a, b), Math.min(a, b));
    }

    // 유클리드 호제법 ->  최대 공약수 구하기
    public static int GCD(int a, int b) { // a > b
        if (a % b == 0) {
            return b;
        }
        return GCD(b, a % b);
    }


}

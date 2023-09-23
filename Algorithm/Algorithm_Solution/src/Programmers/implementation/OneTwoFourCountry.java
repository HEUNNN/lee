package Programmers.implementation;

public class OneTwoFourCountry {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12899
    // 124 나라의 규칙
    // 4보다 작은 수 1, 2, 3일 때 1, 2, 4를 각각 할당
    // 만약 3보다 크면 ?
    // 먼저 n - 1 하고 3으로 나눈 몫이 1, 2, 3 중 하나인지 확인
    // 그 다음 n % 3 이 1, 2, 3 중 하나인지 확인하는 재귀를 반복
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        int n = 10;
        String solution = new OneTwoFourCountry().solution(n);
        System.out.println(solution);
    }

    public String solution(int n) {

        return oneTwoFour(n);
    }

    private String oneTwoFour(int n) {
        if (n == 1) {
            sb.append("1");
        } else if (n == 2) {
            sb.append("2");
        } else if (n == 3 || n == 0) { // 3 % 3 = 0
            sb.append("4");
        } else {
            // 3보다 크면
            oneTwoFour((n - 1) / 3); // ? (n - 1)은 자리 올림을 안 하는 효과
            oneTwoFour(n % 3);

        }
        return sb.toString();
    }
}

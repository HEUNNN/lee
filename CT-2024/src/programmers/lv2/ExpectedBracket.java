package programmers.lv2;


public class ExpectedBracket {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12985
    public static void main(String[] args) {
        int n = 8;
        int a = 3;
        int b = 7;
        int answer = 1;

        while (true) {
            a = (a / 2) + (a % 2);
            b = (b / 2) + (b % 2);
            if (a == b) {
                break;
            }
            answer++;
        }

        System.out.println(answer);
    }

    public static int getDouble(int n) {
        int answer = 0;
        if (n == 2) return 1;
        while (n > 1) {
            answer++;
            n /= 2;
        }
        return answer;
    }
}

package programmers.lv2;

public class NumberExpression {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12924
    public static void main(String[] args) {
        int number = 1;
        int answer = getNumberExpressionCount(number);
        System.out.println(answer);
    }

    public static int getNumberExpressionCount(int number) {
        int answer = 1;

        if (number % 2 == 1 && number != 1) answer++;

        int half = number / 2;

        for (int i = 1; i < half; i++) {
            int sum = 0;
            int add = i;
            while (sum <= number) {
                if (sum == number) {
                    answer++;
                    break;
                }
                sum += add;
                add++;
            }
        }

        return answer;
    }
}

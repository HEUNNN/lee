package Programmers.bruteForce;

public class Carpet {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42842
    public static void main(String[] args) {
        int[] solution = new Carpet().solution(10, 2);
        System.out.println(solution[0]);
        System.out.println(solution[1]);
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        for (int i = (int) Math.sqrt(sum); i >= 1; i--) {
            if (sum % i == 0 && (i - 2) * (sum / i - 2) == yellow) {
                answer[0] = Math.max(i, sum / i);
                answer[1] = Math.min(i, sum / i);
                break;
            }
        }
        return answer;
    }
}

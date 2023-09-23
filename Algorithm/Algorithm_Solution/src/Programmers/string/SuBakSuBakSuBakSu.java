package Programmers.string;

public class SuBakSuBakSuBakSu {
    public static void main(String[] args) {
        String solution = new SuBakSuBakSuBakSu().solution(3);
        System.out.println(solution);
    }

    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i % 2 == 0 ? "수" : "박");
        }
        return sb.toString();
    }
}

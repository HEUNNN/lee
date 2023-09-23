package Programmers.string;

public class HandleStringBasic {
    public static void main(String[] args) {
        String s = "1234";
        boolean solution = new HandleStringBasic().solution(s);
        System.out.println(solution);
    }

    public boolean solution(String s) {
        boolean answer = true;

        if (s.length() != 4 && s.length() != 6) return false;
        for (int i = 0; i < s.length(); i++) {
            int ascii = s.charAt(i) + 0;
            if (ascii < 48 || ascii > 57) {
                return false;
            }
        }
        return answer;
    }

    public boolean solution2(String s) {
        if (s.length() != 4 && s.length() != 6) return false;

        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

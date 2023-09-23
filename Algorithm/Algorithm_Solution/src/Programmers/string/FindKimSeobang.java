package Programmers.string;

public class FindKimSeobang {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12919
    public static void main(String[] args) {
        String[] seoul = new String[]{"Jane", "Kim"};
        String solution = new FindKimSeobang().solution(seoul);
        System.out.println(solution);
    }

    public String solution(String[] seoul) {
        int kim = 0;
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                kim = i;
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append("김서방은 ")
                .append(kim)
                .append("에 있다");
        return sb.toString();
    }
}

package Programmers.string;

import java.util.Stack;

public class DartGame {
    /*
     * 점수: 0 - 10 사이 정수
     * 영역: S(1제곱), D(2제곱), T(3제곱)
     * 옵션: *(바로전의 점수 2배), #(해당 점수 마이너스)
     * 만약 *와 #가 중첩되면 -2배가 된다.
     * '*'는 중첩이된다. 중첩이 된다는 것은 한번 *이 나와서 2배를 한 점수에 또 '*'가 나오면 2배를 할 수 있다는 뜻이다.
     */

    // https://school.programmers.co.kr/learn/courses/30/lessons/17682
    public static void main(String[] args) {
        String dartResult = "1S*2T*3S";
        int solution = new DartGame().solution(dartResult);
        System.out.println(solution);
    }

    public int solution(String dartResult) {
        int answer = 0; // '*' 옵션일 때 중첩을 위해 두번 pop을 하게 되는데, 이 때 null 방지
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 0; i < dartResult.length(); i++) {
            if (dartResult.charAt(i) == 'S') {
                Integer num = st.pop();
                st.push(num);
            } else if (dartResult.charAt(i) == 'D') {
                Integer num = st.pop();
                st.push(num * num);
            } else if (dartResult.charAt(i) == 'T') {
                Integer num = st.pop();
                st.push(num * num * num);
            } else if (dartResult.charAt(i) == '*') {
                int num1 = st.pop();
                int num2 = st.pop();
                st.push(num2 * 2); // 중첩
                st.push(num1 * 2);
            } else if (dartResult.charAt(i) == '#') {
                int num = st.pop();
                st.push(num * -1);
            } else {
                if (dartResult.charAt(i + 1) == '0') {
                    st.push(10);
                    i += 1;
                } else {
                    st.push(Integer.parseInt(String.valueOf(dartResult.charAt(i))));
                }
            }
        }
        while (!st.isEmpty()) {
            answer += st.pop();
        }
        return answer;
    }
}

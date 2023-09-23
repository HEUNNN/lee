package Programmers.bruteForce;

import java.util.ArrayList;
import java.util.List;

public class MaximizeFormulas {
    // https://school.programmers.co.kr/learn/courses/30/lessons/67257
    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        long solution = new MaximizeFormulas().solution(expression);
        System.out.println(solution);
    }

    public long solution(String expression) {
        long answer = 0;
        List<Long> numbers = makeOnlyNumbers(expression);
        List<Character> signs = makeOnlySigns(expression);

        long[] result = new long[6];
        result[0] = operate(numbers, signs, '*', '+', '-');
        result[1] = operate(numbers, signs, '*', '-', '+');
        result[2] = operate(numbers, signs, '+', '*', '-');
        result[3] = operate(numbers, signs, '+', '-', '*');
        result[4] = operate(numbers, signs, '-', '*', '+');
        result[5] = operate(numbers, signs, '-', '+', '*');


        for (int i = 0; i < result.length; i++) {
            if (answer < Math.abs(result[i])) {
                answer = Math.abs(result[i]);
            }
        }
        return answer;
    }

    private long operate(List<Long> numbers, List<Character> signs, char one, char two, char three) {
        List<Long> copyNumbers = new ArrayList<>();
        for (Long number : numbers) { // 깊은 복사
            copyNumbers.add(number);
        }
        List<Character> copySigns = new ArrayList<>();
        for (char sign : signs) {
            copySigns.add(sign);
        }

        calculate(copyNumbers, copySigns, one);
        calculate(copyNumbers, copySigns, two);
        calculate(copyNumbers, copySigns, three);

        return copyNumbers.get(0);
    }

    private void calculate(List<Long> numbers, List<Character> signs, char sign) {
        for (int i = 0; i < signs.size(); i++) {
            if (signs.get(i) == sign) {
                long result = signCalculate(numbers.get(i), numbers.get(i + 1), sign);
                numbers.remove(i);
                numbers.remove(i);
                numbers.add(i, result);
                signs.remove(i);
                i--;
            }
        }
    }

    private long signCalculate(Long number1, Long number2, char sign) {
        if (sign == '*') return number1 * number2;
        else if (sign == '+') return number1 + number2;
        else return number1 - number2;
    }

    private List<Long> makeOnlyNumbers(String expression) {
        List<Long> numbers = new ArrayList<>();
        String tmpNum = "";
        for (int i = 0; i < expression.length(); i++) {
            char target = expression.charAt(i);
            if (target == '*' || target == '+' || target == '-') {
                numbers.add(Long.parseLong(tmpNum));
                tmpNum = "";
            } else {
                tmpNum += expression.charAt(i);
            }
        }
        numbers.add(Long.parseLong(tmpNum));
        return numbers;
    }

    private List<Character> makeOnlySigns(String expression) {
        List<Character> signs = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char target = expression.charAt(i);
            if (target == '*') signs.add('*');
            else if (target == '+') signs.add('+');
            else if (target == '-') signs.add('-');
        }
        return signs;
    }
}

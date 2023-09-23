package Programmers.sort;

import java.util.ArrayList;
import java.util.List;

public class 가장큰수 {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(new 가장큰수().solution(numbers));
    }
    public String solution(int[] numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> numberList = new ArrayList<>();
        for(int number : numbers) {
            numberList.add(String.valueOf(number));
        }
        numberList.sort((x, y) -> (y + x).compareTo(x + y));

        for(String number : numberList) {
            stringBuilder.append(number);
        }
        String result = stringBuilder.toString();

        return result.startsWith("0") ? "0" : result;
    }
    public void asce(List<String> numbers) {
        numbers.sort((x, y) -> x.compareTo(y)); // x > y 이면 x.compareTo(y)의 결과가 양수이기 때문에 두 요소의 순서가 변경됨 → 오름차순 정렬
        for (String number : numbers) {
            System.out.println(number);
        }
    }
    public void desc(List<String> numbers) {
        numbers.sort((x, y) -> y.compareTo(x)); // x > y 이면 x.compareTo(y)의 결과가 양수이기 때문에 두 요소의 순서가 변경됨 → 오름차순 정렬
        for (String number : numbers) {
            System.out.println(number);
        }
    }
}

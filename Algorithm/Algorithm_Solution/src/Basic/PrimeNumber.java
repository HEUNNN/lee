package Basic;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
    // 소수 구하기 → 에라토스테네스의 체
    // ① 구하고자 하는 소수의 범위만큼 1차원 배열을 생성
    // ② 2부터 시작하여 현재 선택된 숫자의 배수에 해당하는 수를 배열의 끝까지 탐색하면서 지운다. 처음으로 선택된 현재 숫자는 지우지 않는다.
    // ③ 배열의 끝까지 ②의 과정을 반복하면 배열에 남아있는 숫자는 모두 소수이다.

    public static void main(String[] args) {
        PrimeNumber primeNumber = new PrimeNumber();
        List<Integer> result = primeNumber.getPrimeNumber(1);
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }

    public List<Integer> getPrimeNumber(int N) {
        // N → 자리수
        List<Integer> primeNumber = new ArrayList<>();
        int lastNumber = lastNumber(N);
        int[] numberArr = new int[lastNumber + 1];

        setNumberArray(numberArr);

        for (int i = 2; i < numberArr.length; i++) {
            if (numberArr[i] != 0) {
                for (int j = i; j < (lastNumber / i) + 1; j++) {
                    numberArr[i * j] = 0;
                }
            }
        }

        for (int i : numberArr) {
            if (numberArr[i] != 0) {
                primeNumber.add(i);
            }
        }
        return primeNumber;
    }

    public int lastNumber(int N) {
        String lastNumber = "";
        for (int i = 0; i < N; i++) {
            lastNumber += "9";
        }
        return Integer.parseInt(lastNumber);
    }

    public void setNumberArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }
}

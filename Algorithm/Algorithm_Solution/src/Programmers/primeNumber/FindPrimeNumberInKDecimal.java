package Programmers.primeNumber;

public class FindPrimeNumberInKDecimal {
    // https://school.programmers.co.kr/learn/courses/30/lessons/92335
    public static void main(String[] args) {
        int solution = new FindPrimeNumberInKDecimal().solution(110011, 10);
        System.out.println(solution);
    }

    public int solution(int n, int k) {
        int answer = 0;
        String[] decimalSplit = getDecimal(n, k).split("0");
        for (int i = 0; i < decimalSplit.length; i++) {
            if (decimalSplit[i].length() == 0) continue;
            if (isPrime(Long.parseLong(decimalSplit[i]))) {
                answer++;
            }
        }
        return answer;
    }

    private String getDecimal(int n, int k) {
        if (k == 10) return String.valueOf(n);
        StringBuilder decimal = new StringBuilder();
        while (n > 0) {
            decimal.insert(0, (n % k)); // 나머지를 앞에 넣어줘야 한다.
            n /= k;
        }
        return decimal.toString();
    }

    private boolean isPrime(long check) {
        if (check <= 1) return false;
        for (long i = 2; i <= Math.sqrt(check); i++) {
            if (check % i == 0) return false;
        }
        return true;
    }
}

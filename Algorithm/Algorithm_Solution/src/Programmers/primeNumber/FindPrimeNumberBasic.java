package Programmers.primeNumber;


public class FindPrimeNumberBasic {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12921
    public static void main(String[] args) {
        int n = 10;
        int solution = new FindPrimeNumberBasic().solution(n);
        System.out.println(solution);
    }
    public int solution(int n) {
        int[] bucket = new int[n + 1];
        initBucket(bucket);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = i + i; j <= n; j += i) {
                if (bucket[j] != 0) bucket[j] = 0;
            }
        }

        int primeNumCnt = 0;
        for (int i = 2; i <= n; i++) {
            if (bucket[i] != 0) primeNumCnt++;
        }

        return primeNumCnt;
    }


    private void initBucket (int[] bucket) {
        int length = bucket.length;
        for (int i = 2; i < length; i++) {
            bucket[i] = i;
        }
    }
}
